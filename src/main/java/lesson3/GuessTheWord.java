package lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        System.out.println("Угадайте слово:");

        String myWord = words[new Random().nextInt(words.length - 1)];
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userWord = scanner.nextLine().toLowerCase();
            if (userWord.equals(myWord)) {
                System.out.println("ВЕРНО!");
                System.out.println("Спасибо за игру!");
                break;
            } else {
                StringBuilder hint = new StringBuilder("Подсказка: ");
                int loop = Math.min(myWord.length(), userWord.length());
                for (int i = 0; i < loop; i++) {
                    char check = myWord.charAt(i);
                    if (check == userWord.charAt(i)) {
                        hint.append(check);
                    } else {
                        hint.append("#");
                    }
                }
                // добиваем до 15-и символов
                for (int i = loop; i < 15; i++) {
                    hint.append("#");
                }
                System.out.println(hint);
            }
        }

    }
}
