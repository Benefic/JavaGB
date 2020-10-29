package ru.geekbrains.java2.network.client.models;

import java.util.Date;

public interface ChatMessage {
    Date getTimestamp();

    String getMessage();
}
