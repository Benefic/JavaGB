package java2.lesson1;

public class Treadmill implements Barrier {

    private static final int DEFAULT_DISTANCE = 100;

    private final int distance;

    public Treadmill() {
        this(DEFAULT_DISTANCE);
    }

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public boolean goThrough(Sportsman sportsman) {
        boolean success = ((Running) sportsman).run() >= distance;
        System.out.printf("%s try to ran over %d, result: %s", sportsman, distance, success);
        System.out.println();
        return success;
    }
}
