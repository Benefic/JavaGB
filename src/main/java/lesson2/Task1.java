package lesson2;

public class Task1 {
    public static void main(String[] args) {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printIntArray(array);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        System.out.println();
        printIntArray(array);
    }


    public static void printIntArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + ",");
        }
    }
}
