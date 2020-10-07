package java2.lesson4.repository;

import java2.lesson4.model.ChatItem;

import java.util.List;

public interface ChatsRepository {
    List<ChatItem> getAllChats();
}
