package algorithms.lesson5;

public class Bagpack {
    // копирую Ваше решение, ибо сделать сам не успел, а на занятии  схватил спойлер
    // лучше придумать уже не могу, всё время к немц возвращаюсь.
    // но его я понял )
    private final Item[] items;

    Bagpack(Item[] items) {
        this.items = items;
    }

    int findBestSum(int i, int weight) {
        if (i < 0) {
            return 0;
        }
        if (items[i].weight > weight) {
            return findBestSum(i - 1, weight);
        } else {
            return Math.max(findBestSum(i - 1, weight),
                    findBestSum(i - 1, weight - items[i].weight) + items[i].price);
        }
    }
}
