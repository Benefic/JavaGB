package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class RegistrationSuccessCommandData implements Serializable {
    private final int userID;

    public RegistrationSuccessCommandData(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "RegistrationSuccessCommandData{" +
                "userID=" + userID +
                '}';
    }
}
