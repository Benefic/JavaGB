package java2.lesson4;

import java2.lesson4.controller.MainChatViewController;
import java2.lesson4.model.ChatItem;
import java2.lesson4.repository.ChatsRepository;
import java2.lesson4.repository.TestChatsRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String MAIN_CHAT_VIEW_FXML = "view/MainChatView.fxml";
    private final ObservableList<ChatItem> chatsData;
    private Stage primaryStage;

    public Main() {
        ChatsRepository repository = new TestChatsRepository();
        chatsData = FXCollections.observableArrayList(repository.getAllChats());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(MAIN_CHAT_VIEW_FXML));

        Parent root = loader.load();

        MainChatViewController controller = loader.getController();
        controller.setMainApp(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Super chat, Telegram killer!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public ObservableList<ChatItem> getChatsData() {
        return chatsData;
    }
}
