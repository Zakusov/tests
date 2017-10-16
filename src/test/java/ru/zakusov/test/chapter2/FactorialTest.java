package ru.zakusov.test.chapter2;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Тест {@link Factorial}.
 */
public class FactorialTest {

    @Test
    public void testFactorial() {
        assertEquals(BigInteger.valueOf(1), Factorial.factorial(0));
        assertEquals(BigInteger.valueOf(1), Factorial.factorial(1));
        assertEquals(BigInteger.valueOf(2), Factorial.factorial(2));
        assertEquals(BigInteger.valueOf(6), Factorial.factorial(3));
        assertEquals(BigInteger.valueOf(24), Factorial.factorial(4));
        assertEquals(BigInteger.valueOf(120), Factorial.factorial(5));
        assertEquals(BigInteger.valueOf(720), Factorial.factorial(6));
        assertEquals(BigInteger.valueOf(5040), Factorial.factorial(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialForNegativeNumber() {
        Factorial.factorial(-1);
        fail("Expected an IllegalArgumentException");
    }
}
