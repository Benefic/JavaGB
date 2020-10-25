package java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        //1.
        System.out.println("Task 1");

        Integer[] arrayInt = new Integer[]{1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arrayInt));
        swapElements(arrayInt, 3, 4);
        System.out.println(Arrays.toString(arrayInt));
        swapElements(arrayInt, 0, 5);
        System.out.println(Arrays.toString(arrayInt));

        System.out.println();

        String[] arrayString = new String[]{"a", "b", "c", "d", "e", "f"};
        System.out.println(Arrays.toString(arrayString));
        swapElements(arrayString, 3, 4);
        System.out.println(Arrays.toString(arrayString));
        swapElements(arrayString, 0, 5);
        System.out.println(Arrays.toString(arrayString));

        //2.
        System.out.println("\n\n");
        System.out.println("Task 2");

        ArrayList<String> strList = arrayToArrayList(arrayString);
        System.out.println(strList.getClass() + ": " + strList);

        ArrayList<Integer> intList = arrayToArrayList(arrayInt);
        System.out.println(intList.getClass() + ": " + intList);

        //3.
        System.out.println("\n\n");
        System.out.println("Task 3");

        Box<Apple> testAppleBox = new Box<>();
        for (int i = 0; i < 10; i++) {
            testAppleBox.add(new Apple());
        }

        Box<Orange> testOrangeBox = new Box<>();
        for (int i = 0; i < 10; i++) {
            testOrangeBox.add(new Orange());
        }

        // тут ошибки - ОК
        //testAppleBox.add(new Orange());
        //testOrangeBox.add(new Apple());
        //testAppleBox.pourInto(testOrangeBox);
        //testOrangeBox.pourInto(testAppleBox);

        System.out.println("boxes have same weight: " + testAppleBox.compare(testOrangeBox));
        for (int i = 0; i < 5; i++) {
            testAppleBox.add(new Apple());
        }
        System.out.println("boxes have same weight: " + testAppleBox.compare(testOrangeBox));

        Box<Apple> testAppleBox1 = new Box<>();
        for (int i = 0; i < 10; i++) {
            testAppleBox1.add(new Apple());
        }

        System.out.println("apple box weight = " + testAppleBox.getWeight());
        System.out.println("apple box1 weight = " + testAppleBox1.getWeight());

        testAppleBox.pourInto(testAppleBox1);

        System.out.println("apple box weight = " + testAppleBox.getWeight());
        System.out.println("apple box1 weight = " + testAppleBox1.getWeight());

    }

    /**
     * 1. Написать метод, который меняет два элемента массива местами.
     * (массив может быть любого ссылочного типа);
     */
    public static <T> void swapElements(T[] array, int firstIndex, int secondIndex) {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    /**
     * 2. Написать метод, который преобразует массив в ArrayList;
     */
    public static <T> ArrayList<T> arrayToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}

