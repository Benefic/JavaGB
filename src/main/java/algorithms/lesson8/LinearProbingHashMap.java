package algorithms.lesson8;

import java.util.Arrays;

public class LinearProbingHashMap<Key, Value> {
    private final int capacity = 7;
    private final Key DELETED;
    private final Key[] keys;
    private final Value[] values;
    private int size;

    public LinearProbingHashMap() {
        DELETED = (Key) new Object();
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private int hash2(Key key) {
        return 7 - (key.hashCode() & 0x7fffffff) % 7;
    }

    private void checkKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        checkKeyNotNull(key);
        if (size == capacity - 1) {
            throw new RuntimeException("Переполнено. Лучше тут сделать перехеширование на массив больше");
        }

        int i = hash(key);
        int step = hash2(key);
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
            i = (i + step) % capacity;
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    public Value get(Key key) {
        checkKeyNotNull(key);
        int i = indexOf(key);
        return i == -1 ? null : values[i];
    }

    public Value remove(Key key) {
        checkKeyNotNull(key);
        int i = indexOf(key);
        if (i >= 0) {
            Value tmp = values[i];
            keys[i] = DELETED;
            values[i] = null;
            return tmp;
        }
        return null;
    }

    private int indexOf(Key key) {
        int i = hash(key);
        int step = hash2(key);
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                return i;
            }
            i = (i + step) % capacity;
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(keys);

    }
}
