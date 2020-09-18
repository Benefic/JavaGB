package lesson8;

import java.util.Arrays;
import java.util.Random;

public class GameImplementation {
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '•';
    private static final Random random = new Random();


    private static int fieldSize = 3;
    private static int dotsToWin = 3;

    private static char[][] gameField = new char[fieldSize][fieldSize];


    private static GameStatus gameStatus = GameStatus.GAME;

    enum GameStatus {
        GAME,
        WIN_HUMAN,
        WIN_AI,
        DRAW
    }

    static void prepareGame() {
        initGameField();
    }

    public static int getFieldSize() {
        return fieldSize;
    }

    public static GameStatus getGameStatus() {
        return gameStatus;
    }

    public static char[][] getGameField() {
        return gameField;
    }

    public static void setFieldSize(int fieldSize) {
        GameImplementation.fieldSize = fieldSize;

    }

    public static int getDotsToWin() {
        return dotsToWin;
    }

    public static void setDotsToWin(int dotsToWin) {
        GameImplementation.dotsToWin = dotsToWin;
    }

    private static void initGameField() {
        gameField = new char[fieldSize][fieldSize];
        for (char[] row : gameField) {
            Arrays.fill(row, DOT_EMPTY);
        }
    }

    static String humanTurn(int rowIndex, int colIndex) {

        if (!isCellValid(rowIndex, colIndex)) {
            return "Cell isn't valid!";
        }

        gameField[rowIndex][colIndex] = DOT_X;
        checkGameStatus();
        return null;
    }

    static void aiTurn() {

        // try to find winning turn
        for (int row = 0; row < fieldSize; row++) {
            for (int column = 0; column < fieldSize; column++) {
                if (isCellValid(row, column)) {
                    gameField[row][column] = DOT_O;
                    if (checkWin(DOT_O)) {
                        return;
                    } else {
                        gameField[row][column] = DOT_EMPTY;
                    }
                }
            }
        }

        // try to find user's plan - check every cell for win
        for (int row = 0; row < fieldSize; row++) {
            for (int column = 0; column < fieldSize; column++) {
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
            rowIndex = random.nextInt(fieldSize);
            colIndex = random.nextInt(fieldSize);
        } while (!isCellValid(rowIndex, colIndex));

        gameField[rowIndex][colIndex] = DOT_O;
    }

    private static boolean isCellValid(int row, int column) {
        if (row < 0 || row >= fieldSize || column < 0 || column >= fieldSize) return false;
        return gameField[row][column] == DOT_EMPTY;
    }

    static void checkGameStatus() {
        if (checkWin(DOT_X)) {
            gameStatus = GameStatus.WIN_HUMAN;
        } else if (checkWin(DOT_O)) {
            gameStatus = GameStatus.WIN_AI;
        } else if (fieldFull()) {
            gameStatus = GameStatus.DRAW;
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
        for (int row = 0; row < fieldSize; row++) {
            dotsInLine = 0;
            for (int column = 0; column < fieldSize; column++) {
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == dotsToWin) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }
        // check columns
        for (int column = 0; column < fieldSize; column++) {
            dotsInLine = 0;
            for (int row = 0; row < fieldSize; row++) {
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == dotsToWin) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }

        // check diagonals
        int diagonalShift = dotsToWin - fieldSize; // сдвиг диагонали от -1 до +1 для случая поля 5х5 и 4 точки на выигрыш

        for (int shift = diagonalShift; shift <= Math.abs(diagonalShift); shift++) {

            // check main diagonal
            dotsInLine = 0;
            for (int cross = 0; cross < fieldSize; cross++) {
                int row = shift < 0 ? cross - shift : cross;
                int column = shift < 0 ? cross : cross + shift;
                if (column >= fieldSize || row >= fieldSize) {
                    continue;
                }
                if (gameField[row][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == dotsToWin) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }

            // check by-diagonal
            dotsInLine = 0;
            for (int cross = 0; cross < fieldSize; cross++) {
                int column = fieldSize - cross - 1 + shift;
                if (column >= fieldSize || column < 0) {
                    continue;
                }
                if (gameField[cross][column] == cellSymbol) {
                    dotsInLine++;
                    if (dotsInLine == dotsToWin) {
                        return true;
                    }
                } else {
                    dotsInLine = 0;
                }
            }
        }


        return false;
    }

}
