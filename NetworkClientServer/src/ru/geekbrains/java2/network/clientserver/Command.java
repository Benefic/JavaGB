package ru.geekbrains.java2.network.clientserver;

import ru.geekbrains.java2.network.clientserver.commands.*;

import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {

    private CommandType type;
    private Object data;

    public static Command authCommand(String login, String password) {
        Command command = new Command();
        command.type = CommandType.AUTH;
        command.data = new AuthCommandData(login, password);
        return command;
    }

    public static Command authOkCommand(String username, int userID) {
        Command command = new Command();
        command.type = CommandType.AUTH_OK;
        command.data = new AuthOkCommandData(username, userID);
        return command;
    }

    public static Command authErrorCommand(String authErrorMessage) {
        Command command = new Command();
        command.type = CommandType.AUTH_ERROR;
        command.data = new AuthErrorCommandData(authErrorMessage);
        return command;
    }

    public static Command errorCommand(String errorMessage) {
        Command command = new Command();
        command.type = CommandType.ERROR;
        command.data = new ErrorCommandData(errorMessage);
        return command;
    }

    public static Command messageInfoCommand(String message, int senderID) {
        Command command = new Command();
        command.type = CommandType.INFO_MESSAGE;
        command.data = new MessageInfoCommandData(message, senderID);
        return command;
    }

    public static Command messageInfoCommand(String message, int senderID, boolean isPublic) {
        Command command = new Command();
        command.type = CommandType.INFO_MESSAGE;
        command.data = new MessageInfoCommandData(message, senderID, isPublic);
        return command;
    }

    public static Command publicMessageCommand(int userID, String message) {
        Command command = new Command();
        command.type = CommandType.PUBLIC_MESSAGE;
        command.data = new PublicMessageCommandData(userID, message);
        return command;
    }

    public static Command privateMessageCommand(int receiverID, String message) {
        Command command = new Command();
        command.type = CommandType.PRIVATE_MESSAGE;
        command.data = new PrivateMessageCommandData(receiverID, message);
        return command;
    }

    public static Command updateUsersListCommand(List<UserData> users) {
        Command command = new Command();
        command.type = CommandType.UPDATE_USERS_LIST;
        command.data = new UpdateUsersListCommandData(users);
        return command;
    }

    public static Command endCommand() {
        Command command = new Command();
        command.type = CommandType.END;
        return command;
    }

    public static Command registrationCommand(String name, String login, String password) {
        Command command = new Command();
        command.type = CommandType.REGISTRATION;
        command.data = new RegistrationCommandData(name, login, password);
        return command;
    }

    public static Command nicknameChangeCommand(String name) {
        Command command = new Command();
        command.type = CommandType.NICKNAME_CHANGE;
        command.data = new NickNameChangeCommandData(name);
        return command;
    }

    public static Command registrationSuccessCommand(int userID) {
        Command command = new Command();
        command.type = CommandType.REGISTRATION_SUCCESS;
        command.data = new RegistrationSuccessCommandData(userID);
        return command;
    }

    public CommandType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
