package java2.lesson1;

public class Cat implements Running, Jumping {

    private static final String DEFAULT_NAME = "Unnamed";

    private final int maxJumpHeight;
    private final int maxRunDistance;
    private final String name;

    public Cat() {
        this(DEFAULT_MAX_JUMP_HEIGHT, DEFAULT_MAX_RUN_DISTANCE, DEFAULT_NAME);
    }

    public Cat(int maxJumpHeight, int maxRunDistance) {
        this(maxJumpHeight, maxRunDistance, DEFAULT_NAME);
    }

    public Cat(int maxJumpHeight, int maxRunDistance, String name) {
        this.maxJumpHeight = maxJumpHeight;
        this.maxRunDistance = maxRunDistance;
        this.name = name;
    }

    public int jump() {
        System.out.println(name + ": try to jump over the wall");
        return maxJumpHeight;
    }

    public int run() {
        System.out.println(name + ": try to run distance");
        return maxRunDistance;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "maxJumpHeight=" + maxJumpHeight +
                ", maxRunDistance=" + maxRunDistance +
                ", name='" + name + '\'' +
                '}';
    }
}
