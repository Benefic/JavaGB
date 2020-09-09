package lesson6;

public class Cat extends Animal {

    private static final float DEFAULT_MAX_JUMP = 2;
    private static final int DEFAULT_MAX_RUN = 200;

    public Cat(String name) {
        this(name, DEFAULT_MAX_RUN, DEFAULT_MAX_JUMP);
    }

    public Cat(String name, int maxRunDistance) {
        this(name, maxRunDistance, DEFAULT_MAX_JUMP);
    }

    public Cat(String name, int maxRunDistance, float maxJumpHeight) {
        this.name = name;
        this.maxRun = maxRunDistance;
        this.maxJump = maxJumpHeight;
    }

    @Override
    public String toString() {
        return "Cat " + name;
    }
}
