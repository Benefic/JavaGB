package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class MessageInfoCommandData implements Serializable {

    private final String message;
    private final int senderID;
    private final boolean isPublic;

    public MessageInfoCommandData(String message, int senderID, boolean isPublic) {
        this.message = message;
        this.senderID = senderID;
        this.isPublic = isPublic;
    }

    public MessageInfoCommandData(String message, int senderID) {
        this(message, senderID, false);
    }

    public String getMessage() {
        return message;
    }

    public int getSender() {
        return senderID;
    }

    public boolean isPublic() {
        return isPublic;
    }

}
