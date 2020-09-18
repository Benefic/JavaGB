package java1.lesson1;

public class CheckNegativeTask {
    public static void main(String[] args) {
        System.out.println(isNegative(5));
        System.out.println(isNegative(-5));
        System.out.println(isNegative(0));
    }

    private static boolean isNegative(int a){
        return a < 0;
    }
}
