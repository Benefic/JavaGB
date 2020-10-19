package ru.geekbrains.java2.network.server.chat.handler;

import ru.geekbrains.java2.network.clientserver.Command;
import ru.geekbrains.java2.network.clientserver.CommandType;
import ru.geekbrains.java2.network.clientserver.commands.AuthCommandData;
import ru.geekbrains.java2.network.clientserver.commands.PrivateMessageCommandData;
import ru.geekbrains.java2.network.clientserver.commands.PublicMessageCommandData;
import ru.geekbrains.java2.network.server.chat.MyServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {

    private static final long AUTH_TIMEOUT = 120_000;
    private final MyServer myServer;
    private final Socket clientSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String username;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        in = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                if (isConnected()) {
                    readMessages();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection!");
                }
            }
        }).start();
    }

    public String getUsername() {
        return username;
    }

    private void readMessages() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            switch (command.getType()) {
                case END -> {
                    return;
                }
                case PRIVATE_MESSAGE -> {
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    String recipient = data.getReceiver();
                    String message = data.getMessage();
                    myServer.sendPrivateMessage(recipient, Command.messageInfoCommand(message, username));
                }
                case PUBLIC_MESSAGE -> {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    String sender = data.getSender();
                    myServer.broadcastMessage(this, Command.messageInfoCommand(message, sender, true));
                }
                default -> System.err.println("Unknown type of command: " + command.getType());
            }

        }
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Unknown type of object from client";
            System.err.println(error);
            e.printStackTrace();
            sendMessage(Command.errorCommand(error));
            return null;
        }
    }

    private void authentication() throws IOException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (username == null) {
                    try {
                        sendMessage(Command.authErrorCommand("Authentication timeout!"));
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, AUTH_TIMEOUT);
        try {
            while (isConnected()) {
                Command command = readCommand();
                if (command == null) {
                    continue;
                }
                if (command.getType() == CommandType.AUTH) {
                    boolean isSuccessAuth = processAuthCommand(command);
                    if (isSuccessAuth) {
                        break;
                    }
                } else {
                    sendMessage(Command.authErrorCommand("Auth command is required!"));
                }

            }
        } catch (IOException e) {
            throw e;
        }
    }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommandData commandData = (AuthCommandData) command.getData();
        String login = commandData.getLogin();
        String password = commandData.getPassword();

        this.username = myServer.getAuthService().getUsernameByLoginAndPassword(login, password);
        if (username != null) {
            if (myServer.isNicknameAlreadyBusy(username)) {
                sendMessage(Command.authErrorCommand("Login and password are already used!"));
                return false;
            }
            sendMessage(Command.authOkCommand(username));
            myServer.broadcastMessage(this, Command.messageInfoCommand(username + " joined to chat!", username, true));
            myServer.subscribe(this);
            return true;
        } else {
            sendMessage(Command.authErrorCommand("Login and/or password are invalid! Please, try again"));
            return false;
        }

    }

    private void closeConnection() throws IOException {
        myServer.unsubscribe(this);
        if (isConnected()) {
            clientSocket.close();
        }
    }

    public void sendMessage(Command command) throws IOException {
        out.writeObject(command);
    }

    private boolean isConnected() {
        return clientSocket != null && clientSocket.isConnected();
    }

}
