package algorithms.lesson6;

public class Main {
    public static void main(String[] args) {
        MyTreeMap<Integer, String> map = new MyTreeMap<>();

        map.put(5, "five");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(2, "two two");

//        System.out.println(map);
//        System.out.println(map.get(2));

//        map.deleteMin();
        System.out.println(map);
        System.out.println(map.size());

        map.delete(5);
        System.out.println(map);
        System.out.println(map.size());


    }
}
