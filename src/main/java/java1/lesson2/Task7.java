package java1.lesson2;

public class Task7 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        move(array, 2);
        Task1.printIntArray(array);
    }

    private static void move(int[] array, int side) {

        // Что-то не даётся, не проверяйте...


        int tmpSrc = array[0];
        int tmpDest;
        side = side % array.length;
        for (int i = 0; i < array.length; i++) {
            int pos = i * side;
            if (pos >= array.length) {
                pos -= array.length;
            }
            tmpDest = array[pos];
            array[pos] = tmpSrc;
            tmpSrc = tmpDest;

        }



//        int tmpSrc = array[0];
//        int tmpDest;
//        for (int i = 0; i < array.length; i++) {
//            int pos = i + side;
//            if (pos >= array.length) {
//                pos -= array.length;
//            }
//            tmpDest = array[pos];
//            array[pos] = tmpSrc;
//            tmpSrc = tmpDest;
//        }
    }
}
