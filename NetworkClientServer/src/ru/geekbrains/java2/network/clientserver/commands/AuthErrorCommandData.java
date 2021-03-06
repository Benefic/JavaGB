package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class AuthErrorCommandData implements Serializable {

    private final String errorMessage;

    public AuthErrorCommandData(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "AuthErrorCommandData{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
