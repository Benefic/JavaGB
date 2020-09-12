package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame {

    public GameSettings() throws HeadlessException {
        setBounds(300, 300, 400, 100);
        setMinimumSize(new Dimension(400, 100));
        setTitle("TikTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));
        setResizable(false);
        final JTextField fieldSize = new JTextField();
        fieldSize.setText(String.valueOf(GameImplementation.getFieldSize()));

        final JTextField dotsToWin = new JTextField();
        dotsToWin.setText(String.valueOf(GameImplementation.getDotsToWin()));

        JButton start = new JButton("START");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameImplementation.setFieldSize(Integer.parseInt(fieldSize.getText()));
                GameImplementation.setDotsToWin(Integer.parseInt(dotsToWin.getText()));
                GameImplementation.prepareGame();
                setVisible(false);
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new GameGiu();
                    }
                });
            }
        });

        add(new JLabel("Field size:"));
        add(fieldSize);
        add(new JLabel("Dots to win:"));
        add(dotsToWin);
        add(start);

        setVisible(true);

    }
}
