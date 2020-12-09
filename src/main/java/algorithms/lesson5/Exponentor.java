package algorithms.lesson5;

public class Exponentor {
    public static double exponentiation(double value, int power) {
        if (value == 0 && power <= 0) {
            throw new ArithmeticException();
        }
        if (power == 0) {
            return 1;
        } else if (power < 0) {
            return 1 / value * exponentiation(value, ++power);
        } else {
            return value * exponentiation(value, --power);
        }
    }
}
