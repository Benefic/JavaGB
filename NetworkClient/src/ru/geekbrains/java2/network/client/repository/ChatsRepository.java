package ru.geekbrains.java2.network.client.repository;


import ru.geekbrains.java2.network.client.models.ChatItem;

import java.util.List;

public interface ChatsRepository {
    List<ChatItem> getAllChats();

    ChatItem getCommonGroup();
}
