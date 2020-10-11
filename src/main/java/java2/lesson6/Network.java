package java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Network {


    public static void incomingThread(DataInputStream in, NetworkUser obj) {
        while (obj.getConnected()) {
            String message = null;
            try {
                message = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (message == null || message.equals("/end")) {
                obj.setConnected(false);
                System.out.println("Connection has been closed");
                break;
            }
            System.out.println("Incoming message: " + message);
        }
    }

    public static void outgoingThread(DataOutputStream out, NetworkUser obj) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (obj.getConnected()) {
                String message = scanner.nextLine();
                out.writeUTF(message);
                if (message.equals("/end")) {
                    obj.setConnected(false);
                    System.out.println("Connection has been closed");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
