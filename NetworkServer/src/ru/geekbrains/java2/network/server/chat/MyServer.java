package ru.geekbrains.java2.network.server.chat;

import ru.geekbrains.java2.network.server.chat.auth.AuthService;
import ru.geekbrains.java2.network.server.chat.auth.BaseAuthService;
import ru.geekbrains.java2.network.server.chat.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final AuthService authService;


    public MyServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.authService = new BaseAuthService();
    }

    public void start() throws IOException {
        System.out.println("Сервер был запущен");

        authService.start();
        try {
            while (!serverSocket.isClosed()) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException e) {
            System.err.println("Failed to accept new connection");
            e.printStackTrace();
        } finally {
            authService.stop();
            serverSocket.close();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание нового подключения....");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Клиент подключился");// /auth login password
        processClientConnection(clientSocket);
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }
            client.sendMessage(message);
        }
    }

    public synchronized void sendMessage(String message, ClientHandler sender) throws IOException {
        String[] parts = message.split("\\s+", 3);
        String command = parts[0];
        String chat = parts[1];
        String messageText = parts[2];
        if (command.equals("/w")) {
            for (ClientHandler client : clients) {
                if (client.getUsername().equals(chat)) {
                    client.sendMessage(sender.getUsername() + " " + chat + " " + messageText);
                    break;
                }
            }
        } else {
            broadcastMessage(command + " " + chat + " " + messageText, sender);
        }
    }

    public synchronized void subscribe(ClientHandler handler) {
        clients.add(handler);
    }

    public synchronized void unsubscribe(ClientHandler handler) {
        clients.remove(handler);
    }

    public synchronized boolean isNicknameAlreadyBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
