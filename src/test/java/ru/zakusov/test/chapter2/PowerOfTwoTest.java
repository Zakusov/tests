package ru.zakusov.test.chapter2;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест {@link PowerOfTwo}.
 */
public class PowerOfTwoTest {

    @Test
    public void isPowerOfTwo() {
        assertFalse(PowerOfTwo.isPowerOfTwo(0));
        assertTrue(PowerOfTwo.isPowerOfTwo(1));
        assertTrue(PowerOfTwo.isPowerOfTwo(-1));
        assertTrue(PowerOfTwo.isPowerOfTwo(2));
        assertTrue(PowerOfTwo.isPowerOfTwo(-2));
        assertFalse(PowerOfTwo.isPowerOfTwo(3));
        assertFalse(PowerOfTwo.isPowerOfTwo(-3));
        assertTrue(PowerOfTwo.isPowerOfTwo(4));
        assertTrue(PowerOfTwo.isPowerOfTwo(-4));
        assertFalse(PowerOfTwo.isPowerOfTwo(5));
        assertFalse(PowerOfTwo.isPowerOfTwo(-5));
    }
}
