package ru.geekbrains.java2.network.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.geekbrains.java2.network.client.controllers.AuthDialogController;
import ru.geekbrains.java2.network.client.controllers.ViewController;
import ru.geekbrains.java2.network.client.models.Network;


public class NetworkChatClient extends Application {

    private Stage primaryStage;
    private Stage authDialogStage;
    private Network network;
    private ViewController viewController;

    public static void showNetworkError(String errorDetails, String errorTitle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Network Error");
        alert.setHeaderText(errorTitle);
        alert.setContentText(errorDetails);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader authLoader = new FXMLLoader();

        authLoader.setLocation(NetworkChatClient.class.getResource("views/authDialog.fxml"));
        Parent authDialogPanel = authLoader.load();
        authDialogStage = new Stage();

        authDialogStage.setTitle("Аутентификая чата");
        authDialogStage.initModality(Modality.WINDOW_MODAL);
        authDialogStage.initOwner(primaryStage);
        authDialogStage.setResizable(false);
        Scene scene = new Scene(authDialogPanel);
        authDialogStage.setScene(scene);
        authDialogStage.show();

        network = new Network();
        if (!network.connect()) {
            showNetworkError("", "Failed to connect to server");
        }

        AuthDialogController authController = authLoader.getController();
        authController.setNetwork(network);
        authController.setClientApp(this);

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(NetworkChatClient.class.getResource("views/view.fxml"));

        Parent root = mainLoader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        viewController = mainLoader.getController();
        viewController.setNetwork(network);

        primaryStage.setOnCloseRequest(event -> network.close());
    }

    public void openChat() {
        viewController.initCommonGroup();
        authDialogStage.close();
        primaryStage.show();
        primaryStage.setTitle(network.getUsername());
        network.waitMessages(viewController);
    }

    public void changeTitle(String title) {
        primaryStage.setTitle(title);
    }
}