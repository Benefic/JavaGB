package java1.lesson2;

public class Task6 {
    public static void main(String[] args) {
        System.out.println(checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        System.out.println(checkBalance(new int[]{2, 2, 2, 2, 2, 2, 10, 1}));
        System.out.println(checkBalance(new int[]{2, 2, 2, 2, 2, 2, 10, 1,1}));
        System.out.println(checkBalance(new int[]{1, 1, 1, 2, 1}));
        System.out.println(checkBalance(new int[]{1, 1, 2, 2, 1}));
    }

    private static boolean checkBalance(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int sumLeft = 0, sumRight = 0;

            for (int j = 0; j <= i; j++) {
                sumLeft += array[j];
            }

            for (int j = i+1; j < array.length; j++) {
                sumRight += array[j];
            }

            if (sumLeft == sumRight) {
                return true;
            }
        }

        return false;
    }
}
