package algorithms.lesson3;

import java.util.Scanner;

public class StringInverter {
    public static void main(String[] args) {
        System.out.println("Please, input your text...");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        MyStack<Character> stack = new MyStack<>(userInput.length());

        for (char c : userInput.toCharArray()) {
            stack.push(c);
        }

        StringBuilder newString = new StringBuilder();

        while (!stack.isEmpty()) {
            newString.append(stack.pop());
        }
        System.out.println(newString.toString());
    }
}
