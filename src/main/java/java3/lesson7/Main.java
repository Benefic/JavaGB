package java3.lesson7;

public class Main {
    public static void main(String[] args) {
        try {
            BatchTesting.start(IntTests.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BatchTesting.start(StringTests.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
