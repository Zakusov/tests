package ru.zakusov.test.chapter6;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PseudoRandomStreamTest {

    @Test
    public void pseudoRandomStream() throws Exception {
        IntStream stream = PseudoRandomStream.pseudoRandomStream(13);
        int[] result = stream.limit(10).toArray();

        int[] expected = {13, 16, 25, 62, 384, 745, 502, 200, 0, 0};
        assertArrayEquals(expected, result);
    }

    @Test
    public void mid() {
        assertEquals(345, PseudoRandomStream.mid(123456));
    }
}