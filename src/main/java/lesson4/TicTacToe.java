package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '•';

    private static final int FIELD_SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char[][] gameField = new char[FIELD_SIZE][FIELD_SIZE];

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static GameStatus gameStatus = GameStatus.GAME;
    private static TurnType currentTurn = TurnType.HUMAN;

    public static void main(String[] args) {
        prepareGame();
        do {
            nextTurn();
            checkGameStatus();
        }
        while (gameStatus == GameStatus.GAME);
        System.out.println("Thank you for playing!");
    }

    private static void prepareGame() {
        System.out.println("Welcome to TicTacToe Game!!!");
        initGameField();
        printField();
    }

    private static void nextTurn() {
        if (currentTurn == TurnType.HUMAN) {
            System.out.println("Your turn!");
            humanTurn();
        } else {
            System.out.println("My turn!");
            aiTurn();
        }
        printField();
        currentTurn = currentTurn == TurnType.HUMAN ? TurnType.AI : TurnType.HUMAN;
    }

    private static void humanTurn() {
        int rowIndex = -1, colIndex = -1;

        while (true) {
            System.out.println("Enter your cell coordinates in 'ROW COLUMN' format (e.g. '1 2')");
            String[] userInput = scanner.nextLine().split(" ");
            if (userInput.length != 2) {
                System.out.println("Invalid data!");
                continue;
            }
            try {
                rowIndex = Integer.parseInt(userInput[0]) - 1;
                colIndex = Integer.parseInt(userInput[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid data!");
            }
            if (!isCellValid(rowIndex, colIndex)) {
                System.out.println("Cell isn't valid!");
            } else {
                break;
            }
        }

        gameField[rowIndex][colIndex] = DOT_X;
        printField();
    }

    private static void aiTurn() {
        // try to find user's plan - check every cell for win
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int column = 0; column < FIELD_SIZE; column++) {
                if (isCellValid(row, column)) {
                    gameField[row][column] = DOT_X;
                    if (checkWin(DOT_X)) {
                        gameField[row][column] = DOT_O;
                        return;
                    } else {
                        gameField[row][column] = DOT_EMPTY;
                    }
                }
            }
        }
        // make random turn
        int rowIndex, colIndex;

        do {
            rowIndex = random.nextInt(FIELD_SIZE);
            colIndex = random.nextInt(FIELD_SIZE);
        } while (!isCellValid(rowIndex, colIndex));

        gameField[rowIndex][colIndex] = DOT_O;
    }

    public static boolean isCellValid(int row, int column) {
        if (row < 0 || row >= FIELD_SIZE || column < 0 || column >= FIELD_SIZE) return false;
        return gameField[row][column] == DOT_EMPTY;
    }

    private static void printField() {
        for (int i = 0; i <= FIELD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int row = 0; row < FIELD_SIZE; row++) {
            System.out.print(row + 1 + " ");
            for (int column = 0; column < FIELD_SIZE; column++) {
                System.out.print(gameField[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void checkGameStatus() {
        if (checkWin(DOT_X)) {
            gameStatus = GameStatus.WIN_HUMAN;
            System.out.println("YOU WIN");
        } else if (checkWin(DOT_O)) {
            gameStatus = GameStatus.WIN_AI;
            System.out.println("AI WINS!");
        } else if (fieldFull()) {
            gameStatus = GameStatus.DRAW;
            System.out.println("IT'S DRAW!");
        }
    }

    private static boolean fieldFull() {
        for (char[] row : gameField) {
            for (char cell : row) {
                if (cell == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkWin(char cellSymbol) {
        int dotsInLine;
        // check rows
        for (int row = 0; row < FIELD_SIZE; row++) {
            dotsInLine = 0;
            for (int column = 0; column < FIELD_SIZE; column++) {
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == DOTS_TO_WIN) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }
        // check columns
        for (int column = 0; column < FIELD_SIZE; column++) {
            dotsInLine = 0;
            for (int row = 0; row < FIELD_SIZE; row++) {
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == DOTS_TO_WIN) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }

        // check diagonals
        int diagonalShift = DOTS_TO_WIN - FIELD_SIZE; // сдвиг диагонали от -1 до +1 для случая поля 5х5 и 4 точки на выигрыш

        for (int shift = diagonalShift; shift <= Math.abs(diagonalShift); shift++) {

            // check main diagonal
            dotsInLine = 0;
            for (int cross = 0; cross < FIELD_SIZE; cross++) {
                int row = shift < 0 ? cross - shift : cross;
                int column = shift < 0 ? cross : cross + shift;
                if (column >= FIELD_SIZE || row >= FIELD_SIZE) {
                    continue;
                }
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == DOTS_TO_WIN) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }

            // check by-diagonal
            dotsInLine = 0;
            for (int cross = 0; cross < FIELD_SIZE; cross++) {
                int column = FIELD_SIZE - cross - 1 + shift;
                if (column >= FIELD_SIZE || column < 0) {
                    continue;
                }
                if (gameField[cross][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == DOTS_TO_WIN) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }


        return false;
    }

    private static void initGameField() {
        for (char[] row : gameField) {
            Arrays.fill(row, DOT_EMPTY);
        }
    }

    enum GameStatus {
        GAME,
        WIN_HUMAN,
        WIN_AI,
        DRAW
    }

    enum TurnType {
        HUMAN,
        AI
    }
}
