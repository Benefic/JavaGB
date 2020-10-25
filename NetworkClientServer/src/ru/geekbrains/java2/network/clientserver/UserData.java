package ru.geekbrains.java2.network.clientserver;

import java.io.Serializable;

public class UserData implements Serializable {
    private final int userID;
    private final String userName;

    public UserData(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
}
