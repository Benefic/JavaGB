package algorithms.lesson2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
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

        Random random = new Random();
        MyArrayList<Integer> mal = new MyArrayList<>(20);
        for (int i = 0; i < 10; i++) {
            mal.add(random.nextInt(100));
        }

        System.out.println(mal);
//        mal.selectionSort(Comparator.naturalOrder());
//        mal.selectionSort(Comparator.reverseOrder());
//        mal.selectionSort((a,b)->{return a%10 - b %10;});

//        mal.insertionSort();
//        mal.bubbleSort();
        System.out.println(mal);
    }
}
