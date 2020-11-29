package algorithms.lesson3;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
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

        MyPriorityQueue<Integer> mpq = new MyPriorityQueue<>(Comparator.reverseOrder());
        mpq.insert(5);
        mpq.insert(2);
        mpq.insert(15);
        mpq.insert(3);

        for (int i = 0; i < 4; i++) {
            System.out.println(mpq.remove());
        }
    }
}
