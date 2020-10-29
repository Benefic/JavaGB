package ru.geekbrains.java2.network.client.util;

import ru.geekbrains.java2.network.client.models.ChatMessage;
import ru.geekbrains.java2.network.client.models.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Logger {

    private static final String SEPARATOR = ";";
    private static final int HISTORY_LIMIT = 100;
    private static String login;

    public static synchronized void logMessage(int chatId, ChatMessage message) {

        if (message == null) {
            throw new IllegalArgumentException("param message must not be null!");
        }
        if (login == null) {
            throw new IllegalStateException("field currentUserName must not be null!");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(chatId)))) {
            writer.write(message.getTimestamp().getTime() + SEPARATOR
                    + message.getMessage());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized List<ChatMessage> readLog(int chatId) {

        if (login == null) {
            throw new IllegalArgumentException("field currentUserName must not be null!");
        }

        List<ChatMessage> history = new LinkedList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(getFileName(chatId)));
            ListIterator<String> listIterator = lines.listIterator(Math.max(0, lines.size() - HISTORY_LIMIT));
            while (listIterator.hasNext()) {
                String element = listIterator.next();
                String[] messageData = element.split(SEPARATOR, 2);
                if (messageData.length == 2) {
                    Date timestamp = new Date(Long.parseLong(messageData[0]));
                    String message = messageData[1];
                    history.add(new Message(timestamp, message));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return history;
    }

    private static String getFileName(int chatId) {
        return "history_" + login + "_" + chatId + ".txt";
    }

    public static void setLogin(String login) {
        Logger.login = login;
    }
}
