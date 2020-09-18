package java1.lesson6;

import java.util.Random;

public class Test {

    public static void main(String[] args) {


        Animal[] animals = new Animal[7]; // в сумме у нас 7 конструкторов, проверим все. Хотя их могло быть и больше...

        // в цикле не делаю, ибо разные контсрукторы надо проверить

        animals[0] = new Cat("Barsik");
        animals[1] = new Cat("Vaska", 100);
        animals[2] = new Cat("Boris", 300, 2.5f);

        animals[3] = new Dog("Tuzik");
        animals[4] = new Dog("Bobik", 400);
        animals[5] = new Dog("Rex", 600, 1);
        animals[6] = new Dog("Sharik", 500, 0.5f, 15);

        Random random = new Random();
        int testCount = 3;
        for (int i = 0; i < testCount; i++) {
            for (Animal animal : animals) {
                animal.run(random.nextInt(10) * 100);
                animal.swim(random.nextInt(3) * 10);
                animal.jump(random.nextFloat() * 5);
                System.out.println("---------------------");
            }
            System.out.println("+++++++++++++++++++++");
        }

    }
}
