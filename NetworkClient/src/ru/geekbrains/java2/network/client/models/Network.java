package ru.geekbrains.java2.network.client.models;

import javafx.application.Platform;
import ru.geekbrains.java2.network.client.controllers.ViewController;
import ru.geekbrains.java2.network.client.repository.TestChatsRepository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {

    private static final String AUTH_CMD_PREFIX = "/auth";
    private static final String AUTHOK_CMD_PREFIX = "/authok";

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8189;

    private final String host;
    private final int port;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;
    private String username;

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
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
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
                getOutputStream().writeUTF(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password));
                String response = getInputStream().readUTF();
                if (response.startsWith(AUTHOK_CMD_PREFIX)) {
                    this.username = response.split("\\s+", 2)[1];
                    return null;
                } else {
                    return response.split("\\s+", 2)[1];
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else {
            return "Соединение не было установлено!";
        }
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void waitMessages(ViewController viewController) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String message = inputStream.readUTF();
                    String[] parts = message.split("\\s+", 3);
                    String chatName = parts[0];
                    String user = parts[1];
                    String messageText = parts[2];
                    ChatItem chat;
                    if (chatName.equals("/common")) {
                        chat = TestChatsRepository.getCurrent().getCommonGroup();
                    } else {
                        chat = TestChatsRepository.getCurrent().getChatByName(chatName);
                    }
                    if (chat != null) {
                        chat.addMessage(String.format("%s: %s", user, messageText));
                        Platform.runLater(() -> viewController.fillChat(chatName));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
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
}
