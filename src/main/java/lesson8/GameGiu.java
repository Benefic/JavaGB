package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGiu extends JFrame {

    private static final int HEADER_HEIGHT = 32;
    private static final int CELL_PADDING = 15;
    private static final int LINE_THICKNESS = 5;
    private static final Color COLOR_O = Color.green;
    private static final Color COLOR_X = Color.BLUE;

    public GameGiu() {
        setBounds(300, 300, 400, 400);
        setMinimumSize(new Dimension(300, 300));
        setTitle("TikTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 super.mouseClicked(e);
                                 checkMouseClicked(e.getX(), e.getY());
                             }
                         }
        );
        setVisible(true);
    }

    private void checkMouseClicked(int x, int y) {

        int height = getCurrentHeight();
        int width = getWidth();
        int fieldSize = GameImplementation.getFieldSize();
        int colIndex = x / (width / fieldSize);
        int rowIndex = (y - HEADER_HEIGHT) / (height / fieldSize);

        System.out.printf("rowIndex: %s colIndex: %s", rowIndex, colIndex);
        System.out.println();

        String result = GameImplementation.humanTurn(rowIndex, colIndex);
        if (result == null) {
            repaint();
            if (GameImplementation.getGameStatus() == GameImplementation.GameStatus.GAME) {
                GameImplementation.aiTurn();
                GameImplementation.checkGameStatus();
                repaint();
            }
            if (GameImplementation.getGameStatus() != GameImplementation.GameStatus.GAME) {
                String message;
                switch (GameImplementation.getGameStatus()) {
                    case DRAW:
                        message = "It's draw";
                        break;
                    case WIN_AI:
                        message = "I'm WIN! Ha-Ha!";
                        break;
                    default:
                        message = "You WIN!";
                }

                JOptionPane.showMessageDialog(null,
                        message,
                        "GAME OVER!",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                System.exit(0);
            }
        } else {
            System.out.println(result);
        }
    }

    private int getCurrentHeight() {
        return getHeight() - HEADER_HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(LINE_THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        char[][] gameField = GameImplementation.getGameField();
        int fieldSize = GameImplementation.getFieldSize();
        int width = getWidth();
        int height = getCurrentHeight();

        for (int i = 1; i < fieldSize; i++) {
            int xPosition = width / fieldSize * i;
            g2d.drawLine(xPosition, 0, xPosition, height + HEADER_HEIGHT);

            int yPosition = height / fieldSize * i + HEADER_HEIGHT;
            g2d.drawLine(0, yPosition, width, yPosition);
        }

        for (int row = 0; row < fieldSize; row++) {
            for (int column = 0; column < fieldSize; column++) {
                char symbol = gameField[row][column];
                if (symbol == GameImplementation.DOT_X || symbol == GameImplementation.DOT_O) {
                    drawCell(g2d, symbol, row, column);
                }
            }
        }

    }

    private void drawCell(Graphics2D g2d, char symbol, int rowIndex, int colIndex) {

        int fieldSize = GameImplementation.getFieldSize();
        int width = getWidth();
        int height = getCurrentHeight();
        int cellWidth = width / fieldSize;
        int cellHeight = height / fieldSize;

        int xPositionStart = cellWidth * colIndex + CELL_PADDING;
        int xPositionEnd = cellWidth * (colIndex + 1) - CELL_PADDING;
        int yPositionStart = cellHeight * rowIndex + HEADER_HEIGHT + CELL_PADDING;
        int yPositionEnd = cellHeight * (rowIndex + 1) + HEADER_HEIGHT - CELL_PADDING;
        if (symbol == GameImplementation.DOT_O) {
            g2d.setColor(COLOR_O);
            g2d.drawOval(xPositionStart, yPositionStart, xPositionEnd - xPositionStart, yPositionEnd - yPositionStart);
        } else {
            g2d.setColor(COLOR_X);
            g2d.drawLine(xPositionStart, yPositionStart, xPositionEnd, yPositionEnd);
            g2d.drawLine(xPositionEnd, yPositionStart, xPositionStart, yPositionEnd);
        }
    }



}
