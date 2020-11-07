package ru.geekbrains.java2.network.client.models;

import javafx.application.Platform;
import ru.geekbrains.java2.network.client.controllers.ViewController;
import ru.geekbrains.java2.network.client.repository.TestChatsRepository;
import ru.geekbrains.java2.network.client.util.Logger;
import ru.geekbrains.java2.network.clientserver.Command;
import ru.geekbrains.java2.network.clientserver.commands.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class Network {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8189;

    private final String host;
    private final int port;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;
    private String username;
    private int userID;

    public Network() {
        this(SERVER_ADDRESS, SERVER_PORT);
    }

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            System.err.println("Соединение не было установлено!");
            e.printStackTrace();
            return false;
        }
    }

    public String sendAuthCommand(String login, String password) {
        if (isConnected()) {
            try {
                Command authCommand = Command.authCommand(login, password);
                Logger.setLogin(login);
                return getCommandResult(authCommand);
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            return "Соединение не было установлено!";
        }
    }

    public String sendRegistrationCommand(String login, String password, String nickname) {
        if (isConnected()) {
            try {
                Command regCommand = Command.registrationCommand(nickname, login, password);
                this.username = nickname;
                Logger.setLogin(login);
                return getCommandResult(regCommand);

            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            return "Соединение не было установлено!";
        }
    }

    private String getCommandResult(Command outCommand) throws IOException {
        outputStream.writeObject(outCommand);
        Command command = readCommand();
        if (command == null) {
            return "Failed to read command from server";
        }
        switch (command.getType()) {
            case AUTH_OK -> {
                AuthOkCommandData data = (AuthOkCommandData) command.getData();
                this.username = data.getUsername();
                this.userID = data.getUserID();
                return null;
            }
            case AUTH_ERROR -> {
                AuthErrorCommandData data = (AuthErrorCommandData) command.getData();
                return data.getErrorMessage();
            }
            case REGISTRATION_SUCCESS -> {
                RegistrationSuccessCommandData data = (RegistrationSuccessCommandData) command.getData();
                this.userID = data.getUserID();
                return null;
            }
            default -> {
                return "Unknown type of command from server: " + command.getType();
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        Command command = Command.publicMessageCommand(userID, message);
        sendCommand(command);
    }

    private void sendCommand(Command command) throws IOException {
        outputStream.writeObject(command);
    }

    public void sendPrivateMessage(String message, int recipient) throws IOException {
        Command command = Command.privateMessageCommand(recipient, message);
        sendCommand(command);
    }

    public void waitMessages(ViewController viewController) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Command command = readCommand();
                    if (command == null) {
                        viewController.showError("Server error", "Invalid command from server");
                        continue;
                    }

                    switch (command.getType()) {
                        case INFO_MESSAGE -> {
                            MessageInfoCommandData data = (MessageInfoCommandData) command.getData();
                            String message = data.getMessage();
                            int sender = data.getSender();
                            boolean isPublic = data.isPublic();
                            ChatItem chat;
                            if (isPublic) {
                                chat = TestChatsRepository.getCurrent().getCommonGroup();
                            } else {
                                chat = TestChatsRepository.getCurrent().getChatByID(sender);
                            }
                            String formattedMessage = sender != 0 ? String.format("%s: %s", TestChatsRepository.getCurrent().getChatByID(sender).getName(), message) : message;
                            ChatMessage chatMessage = new Message(new Date(), formattedMessage);
                            chat.addMessage(chatMessage);
                            Platform.runLater(() -> viewController.fillChat(chat.getName()));
                            Logger.logMessage(chat.getID(), chatMessage);
                        }
                        case ERROR -> {
                            ErrorCommandData data = (ErrorCommandData) command.getData();
                            String errorMessage = data.getErrorMessage();
                            Platform.runLater(() -> viewController.showError("Server error", errorMessage));
                        }
                        case UPDATE_USERS_LIST -> {
                            UpdateUsersListCommandData data = (UpdateUsersListCommandData) command.getData();
                            Platform.runLater(() -> viewController.updateUsers(data.getUsers()));
                        }
                        default -> Platform.runLater(() -> viewController.showError("Unknown command from server", command.getType().toString()));
                    }


                }
            } catch (IOException e) {
                System.out.println("Соединение было потеряно!");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            String error = "Unknown type of object from client";
            System.err.println(error);
            e.printStackTrace();
            return null;
        }
    }

    public void changeNick(String newNick) throws IOException {
        Command command = Command.nicknameChangeCommand(newNick);
        sendCommand(command);
    }
}
