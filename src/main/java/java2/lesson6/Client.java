package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java2.lesson6.Network.incomingThread;
import static java2.lesson6.Network.outgoingThread;

public class Client implements NetworkUser {

    private static final String SERVER_HOST = "localhost";
    private final int port;
    private final String host;
    private boolean connected = false;

    public Client() {
        this(SERVER_HOST, SERVER_PORT);
    }

    public Client(int port) {
        this(SERVER_HOST, port);
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        new Client().start();
    }

    @Override
    public void start() {
        try {
            Socket socket = new Socket(host, port);
            setConnected(true);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Соединение установлено!");

            Thread incoming = new Thread(() -> incomingThread(in, this));
            incoming.setDaemon(true);
            incoming.start();

            Thread outgoing = new Thread(() -> outgoingThread(out, this));
            outgoing.setDaemon(true);
            outgoing.start();

            incoming.join();
            outgoing.join();

        } catch (IOException | InterruptedException e) {
            System.err.println("Соединение не было установлено!");
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
