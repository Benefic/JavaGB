package ru.geekbrains.java2.network.client.models;

import java.util.Date;

public class Message {
    private Date timestamp;
    private String message;

    public Message(String message) {
        this(new Date(), message);
    }

    public Message(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
