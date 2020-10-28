package ru.geekbrains.java2.network.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.geekbrains.java2.network.client.NetworkChatClient;
import ru.geekbrains.java2.network.client.models.Network;

public class AuthDialogController {

    public @FXML
    TextField nicknameField;
    public @FXML
    Button regButton;
    public @FXML
    Label nickNameLabel;
    private @FXML
    TextField loginField;
    private @FXML
    PasswordField passwordField;
    private @FXML
    Button authButton;

    private boolean isRegistration;
    private Network network;
    private NetworkChatClient clientApp;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            NetworkChatClient.showNetworkError("Username and password should be not empty!", "Auth error");
            return;
        }
        if (!network.isConnected()) {
            network.connect();
        }

        if (isRegistration) {
            String nickname = nicknameField.getText();
            if (nickname == null || nickname.isBlank()) {
                NetworkChatClient.showNetworkError("Nickname should be not empty!", "Registration error");
                return;
            }
            executeReg(login, password, nickname);
        } else {
            executeAuth(login, password);
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setClientApp(NetworkChatClient clientApp) {
        this.clientApp = clientApp;
    }

    @FXML
    public void onFieldAction(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (!isRegistration && login != null && !login.isBlank() && password != null && !password.isBlank()) {
            executeAuth(login, password);
        }
    }

    private void executeAuth(String login, String password) {
        String authError = network.sendAuthCommand(login, password);
        if (authError == null) {
            clientApp.openChat();
        } else {
            NetworkChatClient.showNetworkError(authError, "Auth error");
        }
    }

    private void executeReg(String login, String password, String name) {
        String regError = network.sendRegistrationCommand(login, password, name);
        if (regError == null) {
            clientApp.openChat();
        } else {
            NetworkChatClient.showNetworkError(regError, "Registration error");
        }
    }

    public void executeReg(ActionEvent actionEvent) {
        isRegistration = !isRegistration;
        nickNameLabel.setVisible(isRegistration);
        nicknameField.setVisible(isRegistration);
        authButton.setText(isRegistration ? "Зарегистрироваться!" : "Войти!");
    }
}
