package lesson2;

public class Task5 {
    public static void main(String[] args) {
        int[] array = {5, 3, 16, 32, 4, 2, 7, 18};

        int max = 0;
        int min = array[0]; // чтобы не 0, а то не найдём... Наверняка есть способ лучше

        for (int i : array) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        Task1.printIntArray(array);
        System.out.println();
        System.out.println("min = "+ min);
        System.out.println("max = "+ max);
    }
}
