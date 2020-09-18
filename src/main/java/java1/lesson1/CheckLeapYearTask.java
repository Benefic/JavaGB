package java1.lesson1;

public class CheckLeapYearTask {
    public static void main(String[] args) {
        isLeap(1600);
        isLeap(2000);
        isLeap(1700);
        isLeap(1704);
        isLeap(2024);
        isLeap(2021);
        isLeap(0);
    }

    private static void isLeap(int year) {
        if (year == 0) {
            System.out.println("Year " + year + " IS NOT leap! (actually, doesn't exist)");
        } else if (year % 400 == 0) {
            System.out.println("Year " + year + " IS leap!");
        } else if (year % 100 == 0) {
            System.out.println("Year " + year + " IS NOT leap!");
        } else if (year % 4 == 0) {
            System.out.println("Year " + year + " IS leap!");
        } else {
            System.out.println("Year " + year + " IS NOT leap!");
        }
    }
}
