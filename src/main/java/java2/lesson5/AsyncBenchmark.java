package java2.lesson5;

import java.util.Arrays;

public class AsyncBenchmark {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        float[] syncArray = calcSynchronously();
        float[] asyncArray;
        try {
            asyncArray = calcAsynchronously();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Arrays are identical: " + Arrays.equals(syncArray, asyncArray));
    }

    private static float[] calcSynchronously() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long start = System.currentTimeMillis();

        calcArray(arr, 0);

        long finish = System.currentTimeMillis();
        System.out.printf("Synchronously: %s millis%n", finish - start);
        return arr;
    }

    private static float[] calcAsynchronously() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();

        float[] firstArr = new float[HALF];
        float[] secondArray = new float[SIZE - HALF]; // на случай нечётного размера

        System.arraycopy(arr, 0, firstArr, 0, HALF);
        System.arraycopy(arr, HALF, secondArray, 0, SIZE - HALF);

        Thread firstThread = new Thread(() -> calcArray(firstArr, 0));
        Thread secondThread = new Thread(() -> calcArray(secondArray, HALF));

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.arraycopy(firstArr, 0, arr, 0, HALF);
        System.arraycopy(secondArray, 0, arr, HALF, SIZE - HALF);

        long finish = System.currentTimeMillis();
        System.out.printf("Asynchronously: %s millis%n", finish - start);

        return arr;
    }

    private static void calcArray(float[] arr, int iteratorShift) {
        for (int i = 0; i < arr.length; i++) {
            int multiplier = i + iteratorShift;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + multiplier / 5) * Math.cos(0.2f + multiplier / 5) * Math.cos(0.4f + multiplier / 2));
        }
    }

}
