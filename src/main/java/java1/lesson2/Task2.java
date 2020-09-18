package java1.lesson2;

public class Task2 {
    public static void main(String[] args) {

        int[] array = new int[8];
        for (int i = 0; i < array.length; i++) {
            array[i] = i*3;
        }

        Task1.printIntArray(array);
    }
}
