package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class RegistrationCommandData implements Serializable {
    private final String name;
    private final String login;
    private final String password;

    public RegistrationCommandData(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
