package algorithms.lesson4;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> mll = new MyLinkedList<>();
        mll.insertFirst("Maria");
        mll.insertFirst("Katya");
        mll.insertFirst("Luba");

//        for (int i = 0; i < 3; i++) {
//            System.out.println(mll.removeFirst());
//        }

//        mll.insertLast("Maria");
//        mll.insertLast("Katya");
//        mll.insertLast("Luba");
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println(mll.removeLast());
//        }


//        System.out.println(mll.indexOf("Maria"));

//        System.out.println(mll);
//        mll.insert(2, "mmm");
//        System.out.println(mll);
//        System.out.println(mll.remove(2));
////        mll.remove("mmm");
//        System.out.println(mll);
//
//        for (String s : mll) {
//            System.out.println(s);
//        }

//        MyQueue<String> mq = new MyQueue<>();
//        mq.enqueue("qwe");
//        mq.enqueue("asd");
//        mq.enqueue("zxc");

//        for (int i = 0; i < 3; i++) {
//            System.out.println(mq.dequeue());
//        }

        Iterator<String> i = mll.iterator();

        while (i.hasNext()) {
            String s = i.next();
            if (s.equals("Katya")) {
                i.remove();
            }
        }

        System.out.println(mll);

        mll.insertFirst("zzzzzzz");
        mll.insertFirst("yyyyyyy");
        mll.insertFirst("xxxxxxx");
        mll.insertFirst("wwwwwww");

        System.out.println(mll);
        ListIterator<String> li = mll.listIterator();

        System.out.println(li.next());
        System.out.println(li.next());
        System.out.println(li.previous());
        System.out.println(li.previous());
        //System.out.println(li.previous());  - Exception - OK
        System.out.println(li.next());
        System.out.println(li.next());
        System.out.println(li.next());
        System.out.println(li.next());
        System.out.println(li.next());
        System.out.println(li.next());
        //System.out.println(li.next()); - Exception - OK
        li.remove();
        System.out.println(mll);
        System.out.println(li.previous());
        System.out.println(li.previous());
        li.remove();
        System.out.println(mll);
        li.add("aaaaaa");
        System.out.println(mll);
        li.previous();
        System.out.println(li.previous());
        System.out.println(li.next());
        System.out.println(li.next());
        li.add("bbbbbb");
        System.out.println(mll);
        li.next();
        li.next();
        li.add("cccc");
        System.out.println(mll);

        li = mll.listIterator();
        //li.add("dddddd"); - Exception - OK
        li.next();
        li.previous();
        li.add("ddddd");
        System.out.println(mll);
    }
}
