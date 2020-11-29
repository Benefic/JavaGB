package algorithms.lesson2;

import java.util.Date;
import java.util.Random;

public class Main {

    public static final int ARRAY_SIZE = 1_000_000;

    public static void main(String[] args) {

        MyArrayList<Integer> mal = new MyArrayList<>();
        Random random = new Random();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            mal.add(random.nextInt(100_000));
        }
        mal.trim(); // это так, как возможность освобождения ресурсов
        // замеряем алгоритмы сортировки

        // замеряем пузурьковую сортировку
        long start = new Date().getTime();
        mal.bubbleSort();
        long finish = new Date().getTime();

        System.out.println("Сортировка \"пузырьком\": " + (finish - start) + " ms.");


        // замеряем сортировку вставками
        mal = new MyArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            mal.add(random.nextInt(100_000));
        }


        start = new Date().getTime();
        mal.insertionSort();
        finish = new Date().getTime();

        System.out.println("Сортировка \"вставками\": " + (finish - start) + " ms.");

        // замеряем сортировку выбором
        mal = new MyArrayList<>();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            mal.add(random.nextInt(100_000));
        }

        start = new Date().getTime();
        mal.selectionSort();
        finish = new Date().getTime();

        System.out.println("Сортировка \"выбором\": " + (finish - start) + " ms.");


//        MyArrayList<Integer> mal = new MyArrayList<>();
//        mal.add(5);
//        mal.add(2);
//        mal.add(1);
//        mal.add(3);
//        mal.add(4);
//        System.out.println(mal);


//
//        mal.add(3, 99);
//        System.out.println(mal);
//
////        mal.remove((Integer) 1);
////        System.out.println(mal);
//
//        System.out.println(mal.indexOf(44));


//        MySortedArraylist<Integer> msal = new MySortedArraylist<>();
//        msal.add(5);
//        msal.add(2);
//        msal.add(1);
//        msal.add(3);
//        msal.add(4);
//        System.out.println(msal);
//        System.out.println(msal.binaryFind(3));
//
//        Random random = new Random();
//        MyArrayList<Integer> mal = new MyArrayList<>(20);
//        for (int i = 0; i < 10; i++) {
//            mal.add(random.nextInt(100));
//        }

//        System.out.println(mal);
//        mal.selectionSort(Comparator.naturalOrder());
//        mal.selectionSort(Comparator.reverseOrder());
//        mal.selectionSort((a,b)->{return a%10 - b %10;});

//        mal.insertionSort();
//        mal.bubbleSort();
//        System.out.println(mal);
    }

    private void fillArray(MyArrayList arrayList) {

    }
}
