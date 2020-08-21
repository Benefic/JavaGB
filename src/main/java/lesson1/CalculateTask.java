package lesson1;

public class CalculateTask {
    public static void main(String[] args) {
        System.out.println(calculate(5,8,8,7));
        System.out.println(calculate(58.5F,16.7F,36.6F,18));
        System.out.println(calculate(85F,76F,96F,0));
    }

    private static float calculate(float a, float b, float c, float d){
        if (d == 0){
            // Division on 0 protection
            return 0F;
        }
        return a * (b + (c / d));
    }
}
