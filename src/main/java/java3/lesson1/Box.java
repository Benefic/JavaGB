package java3.lesson1;

import java.util.ArrayList;

/**
 * Даны классы Fruit, Apple extends Fruit, Orange extends Fruit; +
 * <p>
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;+
 * <p>
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 * <p>
 * Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
 * вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);+
 * <p>
 * Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
 * которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
 * Можно сравнивать коробки с яблоками и апельсинами; +
 * <p>
 * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 * Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
 * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
 * которые были в первой;+
 * <p>
 * Не забываем про метод добавления фрукта в коробку.
 */
public class Box<T extends Fruit> {
    private final ArrayList<T> box = new ArrayList<>();

    public void add(T fruit) {
        box.add(fruit);
    }

    public float getWeight() {
        if (box.size() == 0) {
            return 0;
        }

        return (float) box.size() * box.get(0).getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return this.getWeight() == box.getWeight();
    }

    public void pourInto(Box<T> box) {
        ArrayList<T> boxToPour = box.getBox();
        this.box.addAll(boxToPour);
        boxToPour.clear();
    }

    private ArrayList<T> getBox() {
        return box;
    }

}
