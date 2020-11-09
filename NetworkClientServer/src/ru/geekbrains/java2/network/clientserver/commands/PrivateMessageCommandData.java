package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class PrivateMessageCommandData implements Serializable {

    private final int receiverID;
    private final String message;

    public PrivateMessageCommandData(int receiver, String message) {
        this.receiverID = receiver;
        this.message = message;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PrivateMessageCommandData{" +
                "receiverID=" + receiverID +
                ", message='" + message + '\'' +
                '}';
    }
}
