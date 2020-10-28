package ru.geekbrains.java2.network.client.repository;

import ru.geekbrains.java2.network.client.models.ChatItem;
import ru.geekbrains.java2.network.client.models.SimpleChatItem;
import ru.geekbrains.java2.network.clientserver.UserData;

import java.util.ArrayList;
import java.util.List;

public class TestChatsRepository implements ChatsRepository {

    private static final ChatItem commonGroup = new SimpleChatItem("*Common group");
    private static List<ChatItem> allChats = new ArrayList<>();
    private static TestChatsRepository current;

    public static TestChatsRepository getCurrent() {
        if (current == null) {
            current = new TestChatsRepository();
        }
        return current;
    }

    public ChatItem getChatByID(int chatID) {
        for (ChatItem chat : allChats) {
            if (chat.getID() == chatID) {
                return chat;
            }
        }
        return null;
    }

    public ChatItem getChat(int chatID, String chatName) {
        ChatItem chat = getChatByID(chatID);
        if (chat == null) {
            chat = new SimpleChatItem(chatName, chatID);
        }
        if (!chat.getName().equals(chatName)) {
            chat.setName(chatName);
        }
        return chat;
    }

    @Override
    public ChatItem getCommonGroup() {
        return commonGroup;
    }

    @Override
    public List<ChatItem> getChats(List<UserData> users) {
        List<ChatItem> chats = new ArrayList<>();
        chats.add(getCommonGroup());
        for (UserData user : users) {
            chats.add(getChat(user.getUserID(), user.getUserName()));
        }
        allChats = chats;
        return allChats;
    }

    @Override
    public List<ChatItem> getAllChats() {
        return allChats;
    }
}
