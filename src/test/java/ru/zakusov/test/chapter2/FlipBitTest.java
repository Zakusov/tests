package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест {@link FlipBit}.
 */
public class FlipBitTest {

    @Test
    public void flipBit() {
        assertEquals(1, flipBitAndPrint(0, 1));
        assertEquals(1, flipBitAndPrint(3, 2));
        assertEquals(6, flipBitAndPrint(4, 2));
        assertEquals(8, flipBitAndPrint(12, 3));
        assertEquals(12, flipBitAndPrint(8, 3));
    }

    private static int flipBitAndPrint(int value, int bitIndex) {
        printBinary(value);
        printBinary(1 << (bitIndex - 1));
        int result = FlipBit.flipBit(value, bitIndex);
        printBinary(result);
        System.out.println();
        return result;
    }

    private static void printBinary(int value) {
        String str = Integer.toBinaryString(value);
        System.out.printf("%8s\r\n", str);
    }
}
