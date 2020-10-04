package java2.lesson4.model;

import java.util.List;

public interface ChatItem {
    List<String> getMessages();

    void addMessage(String message);

    String getName();

    void setName(String name);
}
