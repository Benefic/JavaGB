package algorithms.lesson3;

public class Main {
    public static void main(String[] args) {

        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.insertRight(1);
        deque.insertRight(2);
        deque.insertRight(3);
        deque.insertRight(31);
        deque.insertRight(32);
        deque.insertRight(33);
        System.out.println(deque);

        deque.insertLeft(4);
        System.out.println(deque);

        deque.insertLeft(5);
        System.out.println(deque);

        System.out.println(deque.removeLeft());
        System.out.println(deque);

        System.out.println(deque.removeRight());
        System.out.println(deque);

        deque.insertRight(6);
        System.out.println(deque);

        deque.insertLeft(7);
        System.out.println(deque);

        deque.insertLeft(8);
        System.out.println(deque);
//        MyStack<Integer> stack = new MyStack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//
//        System.out.println(stack.peek());
//        for (int i = 0; i < 4; i++) {
//            System.out.println(stack.pop());
//        }

//        Expression expression = new Expression("( 4-( 5+{ i*p } ) )");
//        System.out.println(expression.checkBracket());


//        MyQueue<Integer> queue = new MyQueue<>(5);
//        System.out.println(queue);
//        queue.insert(5);
//        System.out.println(queue);
//        queue.insert(3);
//        System.out.println(queue);
//        queue.insert(2);
//
//        System.out.println(queue);
//        System.out.println(queue.remove());
//        System.out.println(queue);
//        queue.insert(2);
//        System.out.println(queue);
//        queue.insert(2);
//        System.out.println(queue);
//        queue.insert(2);

//        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<>(Comparator.reverseOrder());
//        mpq.insert(5);
//        mpq.insert(2);
//        mpq.insert(15);
//        mpq.insert(3);
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println(mpq.remove());
//        }
    }
}
