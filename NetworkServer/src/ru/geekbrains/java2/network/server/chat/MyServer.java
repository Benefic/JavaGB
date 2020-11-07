package ru.geekbrains.java2.network.server.chat;

import ru.geekbrains.java2.network.clientserver.Command;
import ru.geekbrains.java2.network.clientserver.UserData;
import ru.geekbrains.java2.network.server.chat.auth.AuthService;
import ru.geekbrains.java2.network.server.chat.auth.DatabaseAuthService;
import ru.geekbrains.java2.network.server.chat.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyServer {

    private final ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final AuthService authService;


    public MyServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.authService = new DatabaseAuthService();
    }

    public void start() throws IOException {
        System.out.println("Сервер был запущен");

        try {
            authService.start();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Unable to create AuthService");
            e.printStackTrace();
            return;
        }
        try {
            while (!serverSocket.isClosed()) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException | ExecutionException | InterruptedException e) {
            System.err.println("Failed to accept new connection");
            e.printStackTrace();
        } finally {
            try {
                authService.stop();
            } catch (SQLException e) {
                System.err.println("Failed to close AuthService");
                e.printStackTrace();
            }
            serverSocket.close();
        }
    }

    private void waitAndProcessNewClientConnection() throws ExecutionException, InterruptedException, IOException {

        ExecutorService clientConnectionService = Executors.newSingleThreadExecutor();
        Future<Boolean> connectionSuccess = clientConnectionService.submit(() -> {

            System.out.println("Ожидание нового подключения....");
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился");// /auth login password\
                processClientConnection(clientSocket);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        });

        if (!connectionSuccess.get()) {
            throw new IOException();
        }
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(ClientHandler sender, Command command, boolean excludeSelf) throws IOException {
        for (ClientHandler client : clients) {
            if (excludeSelf && client == sender) {
                continue;
            }
            client.sendMessage(command);
        }
    }

    public synchronized void broadcastMessage(ClientHandler sender, Command command) throws IOException {
        broadcastMessage(sender, command, true);
    }

    public synchronized void subscribe(ClientHandler handler) throws IOException {
        clients.add(handler);
        broadcastUsersListUpdate();
    }

    public synchronized void unsubscribe(ClientHandler handler) throws IOException {
        clients.remove(handler);
        broadcastUsersListUpdate();
    }

    public void broadcastUsersListUpdate() throws IOException {
        List<UserData> usernames = getAllUserNames();
        broadcastMessage(null, Command.updateUsersListCommand(usernames), true);
    }

    private List<UserData> getAllUserNames() {
        List<UserData> usernames = new ArrayList<>();
        for (ClientHandler client : clients) {
            usernames.add(new UserData(client.getUser().getId(), client.getUsername()));
        }
        return usernames;
    }

    public synchronized boolean isUserAlreadyLogon(User user) {
        for (ClientHandler client : clients) {
            if (client.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public void sendPrivateMessage(int recipient, Command command) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getUser().getId() == recipient) {
                client.sendMessage(command);
            }
        }
    }
}
