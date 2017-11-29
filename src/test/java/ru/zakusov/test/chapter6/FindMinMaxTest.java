package ru.zakusov.test.chapter6;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FindMinMaxTest {

    @Test
    public void findMinMaxForIntegers() throws Exception {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Integer[] minMax = new Integer[2];

        FindMinMax.findMinMax(stream, Integer::compare, (a, b) -> {
            minMax[0] = a;
            minMax[1] = b;
        });

        assertEquals(Integer.valueOf(1), minMax[0]);
        assertEquals(Integer.valueOf(5), minMax[1]);
    }

    @Test
    public void findMinMaxForStrings() throws Exception {
        Stream<String> stream = Stream.of("a", "b", "c");
        String[] minMax = new String[2];

        FindMinMax.findMinMax(stream, String::compareTo, (a, b) -> {
            minMax[0] = a;
            minMax[1] = b;
        });

        assertEquals("a", minMax[0]);
        assertEquals("c", minMax[1]);
    }

    @Test
    public void findMinMaxForEmptyStream() throws Exception {
        Stream<String> stream = Stream.empty();
        String[] minMax = new String[2];

        FindMinMax.findMinMax(stream, String::compareTo, (a, b) -> {
            minMax[0] = a;
            minMax[1] = b;
        });

        assertTrue(minMax[0] == null);
        assertTrue(minMax[1] == null);
    }
}