package java2.lesson4.repository;

import java2.lesson4.model.ChatItem;
import java2.lesson4.model.SimpleChatItem;

import java.util.List;

public class TestChatsRepository implements ChatsRepository {

    @Override
    public List<ChatItem> getAllChats() {
        List<ChatItem> chatItems = List.of(
                new SimpleChatItem("Ivanov Alexey"),
                new SimpleChatItem("Petrov Igor"),
                new SimpleChatItem("Volkov Nikolay"),
                new SimpleChatItem("Kukumber"),
                new SimpleChatItem("Krylov Oleg"),
                new SimpleChatItem("GB University"),
                new SimpleChatItem("Naked News"),
                new SimpleChatItem("Myself"),
                new SimpleChatItem("Wife")
        );
        for (ChatItem chatItem : chatItems) {
            chatItem.addMessage("First message to " + chatItem.getName());
            chatItem.addMessage("Second message to " + chatItem.getName());
            chatItem.addMessage("Third message to " + chatItem.getName());
        }
        return chatItems;
    }
}
