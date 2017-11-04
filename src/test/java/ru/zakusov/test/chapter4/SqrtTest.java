package ru.zakusov.test.chapter4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SqrtTest {

    @Test
    public void sqrt9() {
        assertEquals(3.0, Sqrt.sqrt(9), 1.0E-09);
    }

    @Test
    public void sqrt0() {
        assertEquals(3.0, Sqrt.sqrt(9), 1.0E-09);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sqrtForNegativeValue() {
        Sqrt.sqrt(-9);
        fail("Expected an IllegalArgumentException");
    }
}