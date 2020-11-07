package ru.geekbrains.java2.network.client.models;

import java.util.List;

public interface ChatItem {
    List<ChatMessage> getMessages();

    void addMessage(ChatMessage message);

    String getName();

    int getID();

    void setName(String name);

    boolean isOnline();

    void setOnline(boolean isOnline);
}
