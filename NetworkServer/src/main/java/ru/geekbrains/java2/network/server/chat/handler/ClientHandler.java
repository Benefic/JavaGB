package ru.geekbrains.java2.network.server.chat.handler;

import ru.geekbrains.java2.network.clientserver.Command;
import ru.geekbrains.java2.network.clientserver.CommandType;
import ru.geekbrains.java2.network.clientserver.commands.*;
import ru.geekbrains.java2.network.server.chat.MyServer;
import ru.geekbrains.java2.network.server.chat.User;

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

    private User user;

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
        return user.getUsername();
    }

    public User getUser() {
        return this.user;
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
                    int recipient = data.getReceiverID();
                    String message = data.getMessage();
                    myServer.sendPrivateMessage(recipient, Command.messageInfoCommand(message, user.getId()));
                }
                case PUBLIC_MESSAGE -> {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    int sender = data.getSender();
                    myServer.broadcastMessage(this, Command.messageInfoCommand(message, sender, true));
                }
                case NICKNAME_CHANGE -> {
                    NickNameChangeCommandData data = (NickNameChangeCommandData) command.getData();
                    String name = data.getNickname();
                    if (myServer.getAuthService().changeUserNick(name, user)) {
                        user.setUsername(name);
                        myServer.broadcastUsersListUpdate();
                    } else {
                        sendMessage(Command.authErrorCommand("Error changing nickname!"));
                    }
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
                if (user == null) {
                    try {
                        sendMessage(Command.authErrorCommand("Authentication timeout!"));
                        Thread.sleep(100);
                        closeConnection();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, AUTH_TIMEOUT);
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
            } else if (command.getType() == CommandType.REGISTRATION) {
                boolean isSuccessReg = processRegCommand(command);
                if (isSuccessReg) {
                    break;
                }
            } else {
                sendMessage(Command.authErrorCommand("Auth or registration command is required!"));
            }

        }
    }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommandData commandData = (AuthCommandData) command.getData();
        String login = commandData.getLogin();
        String password = commandData.getPassword();

        this.user = myServer.getAuthService().getUserByLoginAndPassword(login, password);
        if (user != null) {
            if (myServer.isUserAlreadyLogon(user)) {
                sendMessage(Command.authErrorCommand("User is already in!"));
                return false;
            }
            sendMessage(Command.authOkCommand(user.getUsername(), user.getId()));
            myServer.subscribe(this);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            myServer.broadcastMessage(this, Command.messageInfoCommand(user.getUsername() + " joined to chat!", user.getId(), true));
            return true;
        } else {
            sendMessage(Command.authErrorCommand("Login and/or password are invalid! Please, try again"));
            return false;
        }

    }

    private boolean processRegCommand(Command command) throws IOException {
        RegistrationCommandData commandData = (RegistrationCommandData) command.getData();
        String login = commandData.getLogin();
        String password = commandData.getPassword();
        String nickname = commandData.getName();

        User newUser = myServer.getAuthService().getUserByLogin(login);
        if (newUser != null) {
            sendMessage(Command.authErrorCommand("Login is busy!"));
            return false;
        } else {
            newUser = myServer.getAuthService().createUser(login, password, nickname);
            if (newUser == null) {
                sendMessage(Command.authErrorCommand("Failed to register!"));
                return false;
            }
            this.user = newUser;
            sendMessage(Command.registrationSuccessCommand(user.getId()));
            myServer.broadcastMessage(this, Command.messageInfoCommand(user.getUsername() + " joined to chat!", user.getId(), true));
            myServer.subscribe(this);
        }
        return true;
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
