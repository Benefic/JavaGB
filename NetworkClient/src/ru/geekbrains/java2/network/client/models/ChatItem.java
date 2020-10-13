package ru.geekbrains.java2.network.client.models;

import java.util.List;

public interface ChatItem {
    List<String> getMessages();

    void addMessage(String message);

    String getName();

    void setName(String name);
}
