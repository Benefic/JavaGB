package java1.lesson1;

public class CheckSum10to20Task {
    public static void main(String[] args) {
        System.out.println(checkSum( 5,4));
        System.out.println(checkSum( 18,6));
        System.out.println(checkSum( 5,5));
        System.out.println(checkSum( 10,10));
        System.out.println(checkSum( 8,7));
    }

    private static boolean checkSum(int a, int b){
        int sum = a + b;
        return 10 <=sum && sum <=20;
    }

}
