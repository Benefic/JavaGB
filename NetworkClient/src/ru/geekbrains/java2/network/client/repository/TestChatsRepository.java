package ru.geekbrains.java2.network.client.repository;


import ru.geekbrains.java2.network.client.models.ChatItem;
import ru.geekbrains.java2.network.client.models.SimpleChatItem;

import java.util.ArrayList;
import java.util.List;

public class TestChatsRepository implements ChatsRepository {

    private static final ChatItem commonGroup = new SimpleChatItem("*Common group");
    private static final List<ChatItem> allChats = List.of(
            commonGroup,
            new SimpleChatItem("Oleg"),
            new SimpleChatItem("Alexey"),
            new SimpleChatItem("Peter")
    );
    private static TestChatsRepository current;

    public static TestChatsRepository getCurrent() {
        if (current == null) {
            current = new TestChatsRepository();
        }
        return current;
    }

    public ChatItem getChatByName(String name) {
        for (ChatItem chat : allChats) {
            if (chat.getName().equals(name)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public ChatItem getCommonGroup() {
        return commonGroup;
    }

    @Override
    public List<ChatItem> getChats(List<String> users) {
        List<ChatItem> chats = new ArrayList<>();
        chats.add(getCommonGroup());
        for (String user : users) {
            chats.add(getChatByName(user));
        }
        return chats;
    }

    @Override
    public List<ChatItem> getAllChats() {
        return allChats;
    }
}
