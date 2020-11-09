package java3.lesson6;

import java.util.Arrays;

public class Util {

    public int[] getArrayAfterFour(int[] array) {
        if (array == null) {
            throw new RuntimeException();
        }
        int pos = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            throw new RuntimeException();
        }
        return Arrays.copyOfRange(array, pos + 1, array.length);
    }

    public boolean isArrayOfOneAndFour(int[] array) {
        if (array == null) {
            return false;
        }
        int one = 0;
        int four = 0;

        for (int i : array) {
            if (i == 1) {
                one++;
            } else if (i == 4) {
                four++;
            }
        }
        return (one > 0 && four > 0) && array.length == one + four;
    }

}
