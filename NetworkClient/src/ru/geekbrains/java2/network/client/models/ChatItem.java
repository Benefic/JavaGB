package ru.geekbrains.java2.network.client.models;

import java.util.Date;
import java.util.List;

public interface ChatItem {
    List<Message> getMessages();

    void addMessage(String message, Date timestamp);

    String getName();

    void setName(String name);

    boolean isOnline();

    void setOnline(boolean isOnline);
}
