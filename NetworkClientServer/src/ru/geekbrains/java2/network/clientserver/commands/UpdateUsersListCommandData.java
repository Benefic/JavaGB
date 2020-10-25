package ru.geekbrains.java2.network.clientserver.commands;

import ru.geekbrains.java2.network.clientserver.UserData;

import java.io.Serializable;
import java.util.List;

public class UpdateUsersListCommandData implements Serializable {

    private final List<UserData> users;

    public UpdateUsersListCommandData(List<UserData> users) {
        this.users = users;
    }

    public List<UserData> getUsers() {
        return users;
    }
}
