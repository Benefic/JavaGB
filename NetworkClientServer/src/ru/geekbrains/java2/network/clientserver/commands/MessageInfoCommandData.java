package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class MessageInfoCommandData implements Serializable {

    private final String message;
    private final String sender;
    private final boolean isPublic;

    public MessageInfoCommandData(String message, String sender, boolean isPublic) {
        this.message = message;
        this.sender = sender;
        this.isPublic = isPublic;
    }

    public MessageInfoCommandData(String message, String sender) {
        this(message, sender, false);
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public boolean isPublic() {
        return isPublic;
    }

}
