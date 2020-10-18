package ru.geekbrains.java2.network.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.geekbrains.java2.network.client.NetworkChatClient;
import ru.geekbrains.java2.network.client.models.ChatItem;
import ru.geekbrains.java2.network.client.models.Message;
import ru.geekbrains.java2.network.client.models.Network;
import ru.geekbrains.java2.network.client.repository.ChatsRepository;
import ru.geekbrains.java2.network.client.repository.TestChatsRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ViewController {

    @FXML
    public ListView<ChatItem> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;
    private Network network;
    private ChatItem currentChat;
    private ChatItem groupChat;
    ChatsRepository repository = new TestChatsRepository();
    @FXML
    public void initialize() {

        groupChat = repository.getCommonGroup();
        currentChat = groupChat;
        ObservableList<ChatItem> chatsData = FXCollections.observableArrayList(repository.getAllChats());
        usersList.setItems(new SortedList<>(chatsData).sorted());
        usersList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fillChat(newValue);
            setEnabled();
        });
        sendButton.setOnAction(event -> sendMessage());
        textField.setOnAction(event -> sendMessage());
    }

    private void setEnabled() {
        sendButton.setDisable(false);
        textField.setDisable(false);
    }

    private void fillChat(ChatItem chat) {
        currentChat = chat;
        fillChat();
    }

    private void fillChat() {
        chatHistory.clear();
        for (Message message : currentChat.getMessages()) {
            appendMessage(message);
        }
    }

    public void fillChat(String name) {
        if ((currentChat == groupChat && name.equals(groupChat.getName())) || name.equals(currentChat.getName())) {
            fillChat();
        }
    }

    private void sendMessage() {
        String message = textField.getText();
        if (message != null && !message.isBlank()) {
            String myMessage = "Я: " + message;
            appendMessage(new Message(new Date(), myMessage));
            currentChat.addMessage(myMessage, new Date());
            textField.clear();

            try {
                if (currentChat != groupChat) {
                    network.sendPrivateMessage(message, currentChat.getName());
                } else {
                    network.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                String errorMessage = "Failed to send message";
                NetworkChatClient.showNetworkError(e.getMessage(), errorMessage);
            }
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void appendMessage(Message message) {
        String timestamp = DateFormat.getInstance().format(message.getTimestamp());
        chatHistory.appendText(timestamp);
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(message.getMessage());
        chatHistory.appendText(System.lineSeparator());
        chatHistory.appendText(System.lineSeparator());
    }

    public void showError(String title, String message) {
        NetworkChatClient.showNetworkError(message, title);
    }

    public void updateUsers(List<String> users) {
        // убираем ненужное, добавляем новое
        ObservableList<ChatItem> chatsData = FXCollections.observableArrayList(repository.getChats(users));
        usersList.setItems(new SortedList<>(chatsData).sorted());
    }
}