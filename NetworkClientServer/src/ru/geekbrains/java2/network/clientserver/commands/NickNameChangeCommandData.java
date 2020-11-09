package ru.geekbrains.java2.network.clientserver.commands;

import java.io.Serializable;

public class NickNameChangeCommandData implements Serializable {
    private final String nickname;

    public NickNameChangeCommandData(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "NickNameChangeCommandData{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
