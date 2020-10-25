package ru.geekbrains.java2.network.client.repository;


import ru.geekbrains.java2.network.client.models.ChatItem;
import ru.geekbrains.java2.network.clientserver.UserData;

import java.util.List;

public interface ChatsRepository {
    List<ChatItem> getAllChats();

    ChatItem getCommonGroup();

    List<ChatItem> getChats(List<UserData> users);
}
