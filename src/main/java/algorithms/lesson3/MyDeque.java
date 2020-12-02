package algorithms.lesson3;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyDeque<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;
    private int begin;
    private int end;


    public MyDeque(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = (T[]) new Object[capacity];
    }

    public MyDeque() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T peekRight() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[end - 1];
    }

    public T peekLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[begin];
    }

    public T removeRight() {
        T temp = peekRight();
        size--;
        end = nextLeftIndex(end);
        list[end] = null;
        return temp;
    }

    public T removeLeft() {
        T temp = peekLeft();
        size--;
        list[begin] = null;
        begin = nextRightIndex(begin);
        return temp;
    }

    public void insertRight(T item) {
        if (isFull()) {
            reCapacity(size + size / 2);
        }
        size++;
        list[end] = item;
        end = nextRightIndex(end);
    }

    public void insertLeft(T item) {
        if (isFull()) {
            reCapacity(size + size / 2);
        }
        size++;
        begin = nextLeftIndex(begin);
        list[begin] = item;
    }

    private int nextRightIndex(int index) {
        return (index + 1) % list.length;
    }

    private int nextLeftIndex(int index) {
        return index == 0 ? list.length - 1 : --index;
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

    @Override
    public String toString() {
        return Arrays.toString(list) + " begin: " + begin + " end: " + end;
    }

    private void reCapacity(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        // end == begin is always true for full deque
        if (begin == 0) {
            System.arraycopy(list, begin, temp, 0, size);
        } else {
            System.arraycopy(list, begin, temp, 0, list.length - begin);
            System.arraycopy(list, 0, temp, list.length - begin, end);
        }
        list = temp;
        begin = 0;
        end = size;
    }

}
