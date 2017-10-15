package ru.zakusov.test.it;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class PowerOfTwo {

    /**
     * Checks if given <code>value</code> is a power of two.
     *
     * @param value any number
     * @return <code>true</code> when <code>value</code> is power of two, <code>false</code> otherwise
     */
    public static boolean isPowerOfTwo(int value) {
        return Integer.bitCount(Math.abs(value)) == 1;
    }

    /**
     * Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.
     * <p>
     * Решать можно разными способами:
     * <p>
     * воспользовавшись одним удобным статическим методом из класса java.lang.Integer;<br>
     * применив пару трюков из двоичной арифметики;<br>
     * написав решение "в лоб" с циклом и условными операторами (можете вернуться к этой задаче после просмотра соответствующих уроков).
     */
    @Test
    public void isPowerOfTwo() {
        assertFalse(isPowerOfTwo(0));
        assertTrue(isPowerOfTwo(1));
        assertTrue(isPowerOfTwo(-1));
        assertTrue(isPowerOfTwo(2));
        assertTrue(isPowerOfTwo(-2));
        assertFalse(isPowerOfTwo(3));
        assertFalse(isPowerOfTwo(-3));
        assertTrue(isPowerOfTwo(4));
        assertTrue(isPowerOfTwo(-4));
        assertFalse(isPowerOfTwo(5));
        assertFalse(isPowerOfTwo(-5));
    }
}
