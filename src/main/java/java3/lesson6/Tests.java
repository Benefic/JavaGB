package java3.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Tests {
    private Util util;

    public static Stream<Arguments> dataForMassArrayAfterFourTest() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.arguments(new int[]{1, 1, 4, 3, 1, 4, 5, 1}, new int[]{5, 1}));
        out.add(Arguments.arguments(null, null));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{}));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 4, 1, 1, 1}, new int[]{1, 1, 1}));

        out.add(Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, new int[]{}));
        out.add(Arguments.arguments(new int[]{4, 4, 4, 4}, new int[]{}));
        out.add(Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3}, new int[]{3}));

        return out.stream();
    }

    public static Stream<Arguments> dataForIsArrayOfOneAndFourTest() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.arguments(new int[]{1, 1, 4, 3, 1, 4, 5, 1}, false));
        out.add(Arguments.arguments(null, false));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1, 1}, false));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 4, 1, 1, 1}, true));

        out.add(Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, false));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true));
        out.add(Arguments.arguments(new int[]{4, 4, 4, 4}, false));
        out.add(Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3}, false));

        return out.stream();
    }

    @BeforeEach
    public void init() {
        util = new Util();
    }

    @ParameterizedTest
    @MethodSource("dataForMassArrayAfterFourTest")
    public void massArrayAfterFourTest(int[] array, int[] result) {
        try {
            Assertions.assertArrayEquals(result, util.getArrayAfterFour(array));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource("dataForIsArrayOfOneAndFourTest")
    public void massArrayOfOneAndFour(int[] array, boolean result) {
        Assertions.assertEquals(result, util.isArrayOfOneAndFour(array));
    }

}
