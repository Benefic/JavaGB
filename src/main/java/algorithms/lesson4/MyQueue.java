package algorithms.lesson4;

public class MyQueue<T> {
    private final MyLinkedList<T> queue = new MyLinkedList<>();

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(T item) {
        queue.insertFirst(item);
    }

    public T dequeue() {
        return queue.removeLast();
    }

    public T peekFront() {
        return queue.getLast();
    }
}
