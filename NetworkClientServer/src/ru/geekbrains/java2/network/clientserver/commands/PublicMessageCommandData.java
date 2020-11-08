package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class PublicMessageCommandData implements Serializable {

    private final int sender;
    private final String message;

    public PublicMessageCommandData(int sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "PublicMessageCommandData{" +
                "sender=" + sender +
                ", message='" + message + '\'' +
                '}';
    }
}
