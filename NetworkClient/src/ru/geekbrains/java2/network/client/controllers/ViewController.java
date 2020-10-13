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
import ru.geekbrains.java2.network.client.models.Network;
import ru.geekbrains.java2.network.client.repository.ChatsRepository;
import ru.geekbrains.java2.network.client.repository.TestChatsRepository;

import java.io.IOException;

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

    @FXML
    public void initialize() {
        ChatsRepository repository = new TestChatsRepository();
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
        for (String message : currentChat.getMessages()) {
            appendMessage(message);
        }
    }

    public void fillChat(String name) {
        if (name.equals(currentChat.getName()) || (currentChat == groupChat && name.equals("/common"))) {
            fillChat();
        }
    }

    private void sendMessage() {
        String message = textField.getText();
        if (message != null && !message.isBlank()) {
            String myMessage = "Я: " + message;
            appendMessage(myMessage);
            currentChat.addMessage(myMessage);
            textField.clear();

            try {
                if (currentChat != groupChat) {
                    message = String.format("/w %s %s", currentChat.getName(), message);
                } else {
                    message = String.format("/common %s %s", network.getUsername(), message);
                }
                network.getOutputStream().writeUTF(message);
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

    public void appendMessage(String message) {
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
    }
}