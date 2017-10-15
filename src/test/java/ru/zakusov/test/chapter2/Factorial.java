package ru.zakusov.test.chapter2;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Реализуйте метод, вычисляющий факториал заданного натурального числа.
 * <p>
 * Факториал N
 * вычисляется как 1⋅2⋅...⋅N
 * <p>
 * Поскольку это очень быстро растущая функция, то даже для небольших N
 * вместимости типов int и long очень скоро не хватит. Поэтому будем использовать BigInteger.
 */
public class Factorial {

    /**
     * Calculates factorial of given <code>value</code>.
     *
     * @param value positive number
     * @return factorial of <code>value</code>
     */
    public static BigInteger factorial(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Expected a natural number");
        }
        if (value > 1) {
            return BigInteger.valueOf(value).multiply(factorial(value - 1));
        }
        return BigInteger.ONE;
    }

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.valueOf(1), factorial(0));
        assertEquals(BigInteger.valueOf(1), factorial(1));
        assertEquals(BigInteger.valueOf(2), factorial(2));
        assertEquals(BigInteger.valueOf(6), factorial(3));
        assertEquals(BigInteger.valueOf(24), factorial(4));
        assertEquals(BigInteger.valueOf(120), factorial(5));
        assertEquals(BigInteger.valueOf(720), factorial(6));
        assertEquals(BigInteger.valueOf(5040), factorial(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialForNegativeNumber() {
        factorial(-1);
        fail("Expected an IllegalArgumentException");
    }
}
