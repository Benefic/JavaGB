package algorithms.lesson3;

import java.util.Comparator;
import java.util.EmptyStackException;

public class MyPriorityQueue<T extends Comparable<T>> {
    private final int DEFAULT_CAPACITY = 10;
    Comparator<T> comparator;
    private T[] list;
    private int size;

    public MyPriorityQueue(int capacity, Comparator comparator) {
        this(capacity);
        this.comparator = comparator;
    }

    public MyPriorityQueue(Comparator comparator) {
        this();
        this.comparator = comparator;
    }

    public MyPriorityQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = (T[]) new Comparable[capacity];
    }

    public MyPriorityQueue() {
        list = (T[]) new Comparable[DEFAULT_CAPACITY];
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list[size - 1];
    }

    public T remove() {
        T temp = peek();
        size--;
        list[size] = null;
        return temp;
    }

    public void insert(T item) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        list[size] = item;
        size++;

        int i = size - 1;
        // Если Comparator был передан в конструктор то использовать его,
        // если нет то использовать реализацию интерфейса Comparable из класса T.
        if (comparator != null) {
            while (i > 0 && comparator.compare(list[i], list[i - 1]) > 0) {
                swap(i, i - 1);
                i--;
            }
        } else {
            while (i > 0 && list[i].compareTo(list[i - 1]) > 0) {
                swap(i, i - 1);
                i--;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == list.length;
    }

    private void reCapacity(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, temp, 0, size);
        list = temp;
    }

    private void swap(int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }
}
