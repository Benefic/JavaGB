package ru.geekbrains.java2.network.server.chat.auth;

import ru.geekbrains.java2.network.server.chat.User;

import java.sql.SQLException;

public interface AuthService {

    void start() throws ClassNotFoundException, SQLException;

    User getUserByLoginAndPassword(String login, String password);

    void stop() throws SQLException;

    User getUserByLogin(String login);

    User createUser(String login, String password, String nickname);

    boolean changeUserNick(String name, User user);
}
