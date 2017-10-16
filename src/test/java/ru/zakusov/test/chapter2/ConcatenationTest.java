package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConcatenationTest {

    @Test
    public void testConcatenation() {
        System.out.println('A');
        System.out.println('A' + '1');
        System.out.println('A' + '1' + "2");
        assertNotEquals("A12", 'A' + '1' + "2");

        assertEquals("A12", "A" + 12);

        System.out.println('\t' + '\u0003');
        assertEquals("A12", "A" + ('\t' + '\u0003'));

        assertEquals("A12", 'A' + "12");
    }

    @Test
    public void testArray() {
        int[] array = new int[0];
        assertEquals(0, array.length);
    }
}
