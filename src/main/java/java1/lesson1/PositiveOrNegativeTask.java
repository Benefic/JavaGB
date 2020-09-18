package java1.lesson1;

public class PositiveOrNegativeTask {
    public static void main(String[] args) {
        printPositiveOrNegative(5);
        printPositiveOrNegative(-5);
        printPositiveOrNegative(0);
    }

    private static void printPositiveOrNegative(int a){
        if (a < 0) {
            System.out.println(a + " is negative");
        } else {
            System.out.println(a + " is positive");
        }
    }
}
