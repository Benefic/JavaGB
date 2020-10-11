package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java2.lesson6.Network.incomingThread;
import static java2.lesson6.Network.outgoingThread;

public class Server implements NetworkUser {

    private final int port;
    private boolean connected = false;

    public Server() {
        this(SERVER_PORT);
    }

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new Server().start();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for new connection...");
            Socket clientSocket = serverSocket.accept();
            setConnected(true);
            System.out.println("Client has been connected!");

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            Thread incoming = new Thread(() -> incomingThread(in, this));
            incoming.setDaemon(true);
            incoming.start();

            Thread outgoing = new Thread(() -> outgoingThread(out, this));
            outgoing.setDaemon(true);
            outgoing.start();

            incoming.join();
            outgoing.join();

        } catch (IOException | InterruptedException e) {
            System.err.println("Server has been closed");
            setConnected(false);
            e.printStackTrace();
        }
    }

    @Override
    public boolean getConnected() {
        return connected;
    }

    @Override
    public void setConnected(boolean connected) {
        this.connected = connected;
        if (!connected) {
            System.exit(0);
        }
    }
}
