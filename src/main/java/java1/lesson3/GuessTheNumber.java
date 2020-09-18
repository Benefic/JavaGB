package java1.lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        int number = new Random().nextInt(9);
        int tryNumber = 0;
        System.out.println("Угадайте число от 0 до 9:");
        while (true) {
            System.out.println("введите число:");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            tryNumber++;
            int scanned = scanner.nextInt();
            if (scanned == number) {
                System.out.println("ВЕРНО!");
                if (continueGame(scanner)) {
                    number = new Random().nextInt(9);
                    tryNumber = 0;
                } else {
                    System.out.println("Спасибо за игру!");
                    break;
                }
            } else if (tryNumber > 2) {
                System.out.println("НЕ УГАДАЛИ!!");
                if (continueGame(scanner)) {
                    number = new Random().nextInt(9);
                    tryNumber = 0;
                } else {
                    System.out.println("Спасибо за игру!");
                    break;
                }
            } else {
                System.out.printf("Введённое число (%d) %s, чем загаданное%n", scanned, scanned > number ? "больше" : "меньше");
            }
        }
    }

    private static boolean continueGame(Scanner scanner) {
        // ответ  только 1 или 0, остальное считаем ошибкой ввода и просим повторить
        while (true) {
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            int answer = scanner.nextInt();
            if (answer == 1) {
                return true;
            } else if (answer == 0) {
                return false;
            }
        }
    }
}
