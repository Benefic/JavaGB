package algorithms.lesson2;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyArrayList() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        resize();
        list[size] = item;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index: " + index);
        }
    }

    private void resize() {
        if ((float) size / list.length > 0.9) {
            int newCapacity = list.length + list.length / 2;
            list = Arrays.copyOf(list, newCapacity);
        }
    }

    public void trim() {
        if (list.length > size) {
            list = Arrays.copyOf(list, size);
        }
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--;
        list[size] = null;
    }

    public boolean remove(T item) {
        int k = indexOf(item);
        if (k == -1) {
            return false;
        }
        remove(k);
        return true;
    }

    public void add(int index, T item) {
        resize();
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index: " + index);
        }
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = item;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return list[index];
    }

    public void set(int index, T item) {
        checkIndex(index);
        list[index] = item;
    }

    public int size() {
        return size;
    }

    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void selectionSort() {
        selectionSort(Comparator.naturalOrder());
    }

    public void selectionSort(Comparator<T> comparator) {
        int iMin;
        for (int i = 0; i < size - 1; i++) {
            iMin = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(list[j], list[iMin]) < 0) {
                    iMin = j;
                }
            }
            swap(i, iMin);
        }
    }

    public void insertionSort() {
        insertionSort(Comparator.naturalOrder());
    }

    public void insertionSort(Comparator<T> comparator) {
        T key;
        for (int i = 1; i < size; i++) {
            int j = i;
            key = list[i];
            while (j > 0 && comparator.compare(key, list[j - 1]) < 0) {
                list[j] = list[j - 1];
                j--;
            }
            list[j] = key;
        }
    }

    public void bubbleSort() {
        bubbleSort(Comparator.naturalOrder());
    }

    public void bubbleSort(Comparator<T> comparator) {
        boolean isSwap;
        for (int i = size - 1; i > 0; i--) {
            isSwap = false;
            for (int j = 0; j < i; j++) {
                if (comparator.compare(list[j + 1], list[j]) < 0) {
                    swap(j, j + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) {
                System.out.println("break " + i);
                break;
            }
        }
    }

}
