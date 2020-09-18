package java1.lesson7;

public class Plate {

    private int food;

    class NotEnoughFoodException extends Exception {
        // Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды  ))
    }

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void decreaseFood(int appetite) throws NotEnoughFoodException {
        if (appetite > food) {
            throw new NotEnoughFoodException();
        }
        this.food -= appetite; // food = food - appetite;
    }

    public void increaseFood(int quantity) {
        this.food += quantity;
    }


}
