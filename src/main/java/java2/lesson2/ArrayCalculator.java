package java2.lesson2;

import java.util.LinkedList;
import java.util.List;

public class ArrayCalculator {

    private static final int ARRAY_SIZE = 4;

    public static void main(String[] args) {

        List<String[][]> arrays = new LinkedList<>();

        arrays.add(new String[][]{{"1", "2", "1", "4"}, {"1", "2", "2", "5"}, {"1", "2", "3", "6"}, {"1", "2", "2", "7"}}); // valid
        arrays.add(new String[][]{{"1", "2", "1", "4"}, {"1", "2", "5"}, {"1", "2", "3", "6"}, {"1", "2", "2", "2", "7"}});
        arrays.add(new String[][]{{"1", "2", "1", "4"}, {"1", "2", "3", "6"}, {"1", "2", "2", "2", "7"}});
        arrays.add(new String[][]{{"1", "2", "1", "4"}, {"1", "2", "f", "5"}, {"1", "2", "3", "6"}, {"1", "2", "2", "7"}});

        for (String[][] array : arrays) {
            try {
                int result = calcArrayElements(array);
                System.out.println(result);
            } catch (MyArraySizeException | MyArrayDataException e) {
                e.printStackTrace();
            }
        }

    }

    private static int calcArrayElements(String[][] stringArray) {
        if (stringArray.length != ARRAY_SIZE) {
            throw new MyArraySizeException();
        }

        for (String[] array : stringArray) {
            if (array.length != ARRAY_SIZE) {
                throw new MyArraySizeException();
            }
        }

        int result = 0;
        for (int row = 0; row < ARRAY_SIZE; row++) {
            for (int column = 0; column < ARRAY_SIZE; column++) {
                String s = stringArray[row][column];
                try {
                    result += Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("Недопустимое значение в ячейке [%d][%d]: %s", row, column, s));
                }
            }
        }
        return result;
    }

}
