package ru.geekbrains.java2.network.server;

import org.apache.log4j.Logger;
import ru.geekbrains.java2.network.server.chat.MyServer;

import java.io.IOException;


public class ServerApp {

    private static final int DEFAULT_PORT = 8189;
    private static final Logger Log = Logger.getLogger(ServerApp.class);

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }
        try {
            new MyServer(port).start();
        } catch (IOException e) {
            Log.error("Failed to create MyServer", e);
            System.exit(1);
        }
    }

}
