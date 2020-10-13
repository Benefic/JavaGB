package ru.geekbrains.java2.network.client.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;
import java.util.List;

public class SimpleChatItem implements ChatItem {

    private final List<String> messages = new LinkedList<>();
    private StringProperty name;

    public SimpleChatItem(String name) {
        setName(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(String message) {
        messages.add(message);
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }


}
