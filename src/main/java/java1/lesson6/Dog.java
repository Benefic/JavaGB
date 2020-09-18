package java1.lesson6;

public class Dog extends Animal {

    private static final float DEFAULT_MAX_JUMP = 0.5f;
    private static final int DEFAULT_MAX_RUN = 500;
    private static final int DEFAULT_MAX_SWIM = 10;

    public Dog(String name) {
        this(name, DEFAULT_MAX_RUN, DEFAULT_MAX_JUMP, DEFAULT_MAX_SWIM);
    }

    public Dog(String name, int maxRunDistance) {
        this(name, maxRunDistance, DEFAULT_MAX_JUMP, DEFAULT_MAX_SWIM);
    }

    public Dog(String name, int maxRunDistance, float maxJumpHeight) {
        this(name, maxRunDistance, maxJumpHeight, DEFAULT_MAX_SWIM);
    }

    public Dog(String name, int maxRunDistance, float maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRun = maxRunDistance;
        this.maxJump = maxJumpHeight;
        this.maxSwim = maxSwimDistance;
    }

    @Override
    public String toString() {
        return "Dod " + name;
    }
}
