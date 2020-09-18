package java1.lesson6;

public abstract class Animal {

    protected String name;
    protected int maxRun = 0;
    protected int maxSwim = 0;
    protected float maxJump = 0;

    public void run(int distance) {
        String result;
        if (maxRun == 0) {
            result = "can not run!";
        } else if (distance == 0){
            result = "distance is 0, just stay...";
        } else if (distance <= maxRun) {
            result = "ran " + distance + " m.";
        } else {
            result = "ran only " + maxRun + " m. of " + distance + " m.";
        }
        System.out.println(toString() + " " + result);
    }

    public void swim(int distance) {
        String result;
        if (maxSwim == 0) {
            result = "can not swim!";
        } else if (distance == 0){
            result = "distance is 0, just stay...";
        } else if (distance <= maxSwim) {
            result = "swam " + distance + " m.";
        } else {
            result = "swam only " + maxSwim + " m. of " + distance + " m.";
        }
        System.out.println(toString() + " " + result);
    }

    public void jump(float height) {
        String result;
        if (maxJump == 0) {
            result = "can not jump!";
        } else if (height == 0){
            result = "height is 0, just stay...";
        } else if (height <= maxJump) {
            result = "jumped over " + height + " m.";
        } else {
            result = "can not jump over " + height + " m., max is " + maxJump + " m.";
        }
        System.out.println(toString() + " " + result);

    }


}
