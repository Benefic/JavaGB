package java2.lesson2;

public class MyArraySizeException extends IllegalArgumentException {

    private static final String DEFAULT_MESSAGE = "Неверная размерность массива";

    public MyArraySizeException() {
        this(DEFAULT_MESSAGE);
    }

    public MyArraySizeException(String s) {
        super(s);
    }
}
