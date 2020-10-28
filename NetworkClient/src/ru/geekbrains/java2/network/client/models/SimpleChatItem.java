package ru.geekbrains.java2.network.client.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimpleChatItem implements ChatItem {

    private final List<Message> messages = new LinkedList<>();
    private StringProperty name;
    private boolean isOnline;
    private IntegerProperty ID;

    public SimpleChatItem(String name, int ID) {
        setName(name);
        setID(ID);
    }

    public SimpleChatItem(String name) {
        this(name, 0);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addMessage(String message, Date timestamp) {
        messages.add(new Message(timestamp, message));
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public int getID() {
        return ID.intValue();
    }

    @Override
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setID(int ID) {
        this.ID = new SimpleIntegerProperty(ID);
    }

    @Override
    public boolean isOnline() {
        return isOnline;
    }

    @Override
    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }


}
