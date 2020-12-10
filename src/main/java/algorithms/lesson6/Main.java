package algorithms.lesson6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        int balanced = 0;
        int mapCount = 20;
        for (int i = 0; i < mapCount; i++) {
            MyTreeMap<Integer, Integer> map = new MyTreeMap<>();
            while (map.height() < 5) {  // 6 уровень - высота 5
                int keyValue = random.nextInt(201) - 100; // диапазон -100 : 100 включительно
                map.put(keyValue, keyValue);
            }
            //System.out.printf("map %d, size: %d\n", i, map.size());
            if (map.isBalanced()) {
                balanced++;
            }
        }

        System.out.printf("Percent of balance: %.2f%% \n", ((float) balanced / mapCount) * 100);

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

        System.out.println(map.height());

        map.delete(5);
        System.out.println(map);
        System.out.println(map.size());

        MyTreeMap<String, String> stringMap = new MyTreeMap<>();
        stringMap.put("s", "s");
        stringMap.put("e", "e");
        stringMap.put("a", "a");
        stringMap.put("r", "r");
        stringMap.put("c", "c");
        stringMap.put("h", "h");
        stringMap.put("x", "x");

        System.out.println(stringMap);
        System.out.println(stringMap.size());

        System.out.println(stringMap.height());

    }
}
