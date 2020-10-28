package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class AuthOkCommandData implements Serializable {

    private final String username;
    private final int userID;

    public AuthOkCommandData(String username, int userID) {
        this.username = username;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }
}
