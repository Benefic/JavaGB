package java2.lesson3;

import java.util.*;

public class Task1Array {

    private static final String[] TEST_ARRAY = {
            "One", "Nine", "Three", "Seven",
            "Five", "Ten", "Two", "Four",
            "Seven", "Two", "Four", "Eight",
            "Nine", "Two", "Ten", "Four",
            "Four", "Eight", "Six", "Four",
    };

    public static void main(String[] args) {

        Set<String> strings = new HashSet<>();
        Collections.addAll(strings, TEST_ARRAY);

        System.out.println("Unique words:");
        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println();
        System.out.println("Counting words");

        Map<String, Integer> map = new HashMap<>();
        for (String s : TEST_ARRAY) {
            int wordCount = map.getOrDefault(s, 0);
            map.put(s, ++wordCount);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf("The word '%s' appears in the array %d time(s)", entry.getKey(), entry.getValue());
            System.out.println();
        }
    }

}
