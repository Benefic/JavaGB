package java1.lesson7;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random random = new Random();

        int catsCount = random.nextInt(10) + 1;
        Cat[] cats = new Cat[catsCount];
        for (int i = 0; i < catsCount; i++) {
            cats[i] = new Cat("Cat " + i, random.nextInt(50) + 1);
        }

        Plate plate = new Plate(random.nextInt(10) * 10);
        plate.info();

        for (Cat cat : cats) {
            if (cat.getAppetite() >= plate.getFood()) {
                cat.eat(plate);
            }
            System.out.println(cat);
            plate.info();
            if (!cat.isSatiety()) {
                plate.increaseFood(random.nextInt(10) * 5);
                plate.info();
            }
        }

        for (Cat cat : cats) {
            System.out.println("Cat " + cat.getName() + ", satiety: " + cat.isSatiety());
        }

    }
}
