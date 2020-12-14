package algorithms.lesson5;

public class Main {
    public static void main(String[] args) {
        System.out.printf("2^2 = %.2f \n", Exponentor.exponentiation(2, 2));
        System.out.printf("5^2 = %.2f \n", Exponentor.exponentiation(5, 2));
        System.out.printf("5^-2 = %.2f \n", Exponentor.exponentiation(5, -2));
        //System.out.printf("0^-2 = %.2f \n", Exponentor.exponentiation(0,-2));
        System.out.printf("4^0 = %.2f \n", Exponentor.exponentiation(4, 0));
    }
}
