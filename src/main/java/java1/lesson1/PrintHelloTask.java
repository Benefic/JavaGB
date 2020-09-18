package java1.lesson1;

public class PrintHelloTask {
    public static void main(String[] args) {
        printHello("Василий Петрович");
        printHello("Игорь");
        printHello("Мадест");
        printHello(null);
    }

    private static void printHello(String name){
        System.out.println("Привет, " + (name == null ? "Мир":name) + "!");
    }
}
