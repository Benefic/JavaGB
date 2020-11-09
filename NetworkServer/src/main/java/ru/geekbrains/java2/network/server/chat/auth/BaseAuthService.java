package ru.geekbrains.java2.network.server.chat.auth;

import org.apache.log4j.Logger;
import ru.geekbrains.java2.network.server.chat.User;

import java.util.List;
import java.util.Random;

public class BaseAuthService implements AuthService {

    private static final Logger Log = Logger.getLogger(BaseAuthService.class);


    private static final List<User> USERS = List.of(
            new User(0, "login1", "pass1", "Oleg"),
            new User(1, "login2", "pass2", "Alexey"),
            new User(2, "login3", "pass3", "Peter")
    );

    @Override
    public void start() {
        Log.info("Auth service has been started");
    }

    @Override
    public void stop() {
        Log.info("Auth service has been finished");
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : USERS) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User createUser(String login, String password, String nickname) {
        return new User(new Random().nextInt(), login, password, nickname);
    }

    @Override
    public boolean changeUserNick(String name, User user) {
        user.setUsername(name);
        return true;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        for (User user : USERS) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
