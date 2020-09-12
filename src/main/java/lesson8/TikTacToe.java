package lesson8;

import java.awt.*;

public class TikTacToe {
    public static void main(String[] args) {
        GameImplementation.prepareGame();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameSettings();
            }
        });
    }
}
