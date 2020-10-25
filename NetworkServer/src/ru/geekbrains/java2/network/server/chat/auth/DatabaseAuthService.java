package ru.geekbrains.java2.network.server.chat.auth;

import ru.geekbrains.java2.network.server.chat.User;

import java.sql.*;

public class DatabaseAuthService implements AuthService {

    private static final String URL = "jdbc:sqlite:InnerDB.s3db";
    private static final String USERS_TABLE = "users";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";


    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void createDB() throws SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists '" + USERS_TABLE + "' ('" + USER_ID + "' INTEGER default 1 not null primary key autoincrement, '" + USER_NAME + "' TEXT, '" + USER_LOGIN + "' TEXT, '" + USER_PASSWORD + "' TEXT);");
    }

    @Override
    public void start() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(URL);
        createDB();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT " + USER_ID + "," + USER_NAME + " FROM " + USERS_TABLE + " WHERE " + USER_LOGIN + " = ? AND " + USER_PASSWORD + " = ?")) {

            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(USER_ID), rs.getString(USER_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT " + USER_ID + "," + USER_NAME + " FROM " + USERS_TABLE + " WHERE " + USER_LOGIN + " = ?")) {

            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(USER_ID), rs.getString(USER_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User createUser(String login, String password, String nickname) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into " + USERS_TABLE + "(" + USER_NAME + "," + USER_LOGIN + "," + USER_PASSWORD + ") values(?, ?, ?)"
        )) {

            statement.setString(1, nickname);
            statement.setString(2, login);
            statement.setString(3, password);

            statement.execute();
            // ошибки не было, пользователь успешно добавлен, можно извлекать его ID
            user = getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean changeUserNick(String name, User user) {
        try (PreparedStatement statement = connection.prepareStatement(
                "update " + USERS_TABLE + " set " + USER_NAME + " = ? where " + USER_ID + " = ?"
        )) {

            statement.setString(1, name);
            statement.setInt(2, user.getId());

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void stop() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
