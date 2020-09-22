package java2.lesson1;

public class Wall implements Barrier {

    private static final int DEFAULT_HEIGHT = 1;

    private final int height;

    public Wall() {
        this(DEFAULT_HEIGHT);
    }

    public Wall(int height) {
        this.height = height;
    }

    public boolean goThrough(Sportsman sportsman) {
        boolean success = ((Jumping) sportsman).jump() >= height;
        System.out.printf("%s try to jump over %d, result: %s", sportsman, height, success);
        System.out.println();
        return success;
    }
}
