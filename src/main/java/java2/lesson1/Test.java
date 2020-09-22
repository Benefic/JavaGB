package java2.lesson1;

import java.util.Random;

public class Test {

    private final static Random random = new Random();
    private final static int SPORTSMAN_COUNT = 10;
    private final static int BARRIER_COUNT = 10;
    private final static int MAX_WALL_HEIGHT = 5;
    private final static int MAX_TREADMILL_DISTANCE = 100;

    public static void main(String[] args) {
        Sportsman[] sportsmenArray = new Sportsman[SPORTSMAN_COUNT];
        for (int i = 0; i < SPORTSMAN_COUNT; i++) {
            sportsmenArray[i] = getRandomSportsman(i);
        }

        Barrier[] barriersArray = new Barrier[BARRIER_COUNT];
        for (int i = 0; i < BARRIER_COUNT; i++) {
            barriersArray[i] = getRandomBarrier();
        }

        for (Sportsman sportsman : sportsmenArray) {
            boolean isWinner = true;
            for (Barrier barrier : barriersArray) {
                if (!barrier.goThrough(sportsman)) {
                    System.out.println("Abort!");
                    isWinner = false;
                    break;
                }
            }
            if (isWinner) {
                System.out.println("Winner!");
            }
            System.out.println("-------------------------");
        }

    }

    private static Sportsman getRandomSportsman(int suffix) {
        int fate = random.nextInt(3);
        Sportsman result;
        switch (fate) {
            case 0:
                result = new Human(random.nextInt(MAX_WALL_HEIGHT), random.nextInt(MAX_TREADMILL_DISTANCE), "Human " + suffix);
                break;
            case 1:
                result = new Cat(random.nextInt(MAX_WALL_HEIGHT), random.nextInt(MAX_TREADMILL_DISTANCE), "Cat " + suffix);
                break;
            case 2:
                result = new Robot(random.nextInt(MAX_WALL_HEIGHT), random.nextInt(MAX_TREADMILL_DISTANCE), "Robot " + suffix);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fate);
        }
        return result;
    }

    private static Barrier getRandomBarrier() {
        int fate = random.nextInt(2);
        Barrier result;
        switch (fate) {
            case 0:
                result = new Wall(random.nextInt(MAX_WALL_HEIGHT));
                break;
            case 1:
                result = new Treadmill(random.nextInt(MAX_TREADMILL_DISTANCE));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fate);
        }
        return result;
    }
}
