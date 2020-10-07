package java2.lesson4.controller;

import java2.lesson4.Main;
import java2.lesson4.model.ChatItem;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainChatViewController {

    private static final double CHAT_BOX_MARGIN = 20;
    public TextField editMessage;
    public VBox chatBox;
    public Button buttonSend;

    @FXML
    private ListView<ChatItem> chatsList;
    private Main mainApp;
    private ChatItem currentChat;


    public void setMainApp(Main main) {
        mainApp = main;
        chatsList.setItems(new SortedList<>(mainApp.getChatsData()).sorted());
        chatsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fillChat(newValue);
            setEnabled();
        });
    }

    private void setEnabled() {
        buttonSend.setDisable(false);
        editMessage.setDisable(false);
    }

    private void fillChat(ChatItem chat) {
        currentChat = chat;

        ObservableList<Node> chatNodes = chatBox.getChildren();
        chatNodes.clear();

        for (String message : currentChat.getMessages()) {
            addMessageToBox(message);
        }

    }

    @FXML
    public void onEnter(ActionEvent ae) {
        addMessage(editMessage.getText());
        editMessage.clear();
    }

    private void addMessage(String text) {
        currentChat.addMessage(text);
        addMessageToBox(text);
    }

    private void addMessageToBox(String message) {
        Text text = new Text(message);
        text.setWrappingWidth(chatBox.getWidth() - CHAT_BOX_MARGIN);
        text.setTextAlignment(TextAlignment.RIGHT);
        chatBox.getChildren().add(text);
    }

}
