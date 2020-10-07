package java2.lesson3;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {

    private final Map<String, ArrayList<String>> catalog = new TreeMap<>();

    public static void main(String[] args) {
        Phonebook testBook = new Phonebook();
        testBook.add("Smith", "555-456-85-96");
        testBook.add("Johnson", "555-446-74-96");
        testBook.add("Stark", "555-444-33-22");
        testBook.add("Smith", "555-333-55-77");
        testBook.add("Rives", "555-333-77-77");

        System.out.println(testBook.get("Johnson"));
        System.out.println(testBook.get("Smith"));
    }

    public void add(String surname, String phone) {
        ArrayList<String> phonesList = catalog.getOrDefault(surname, new ArrayList<>());
        phonesList.add(phone);
        catalog.put(surname, phonesList);
    }

    public String get(String surname) {
        ArrayList<String> phonesList = catalog.get(surname);
        if (phonesList == null) {
            return null;
        }

        return phonesList.toString().replaceAll("[\\[\\]]", "");
    }
}
