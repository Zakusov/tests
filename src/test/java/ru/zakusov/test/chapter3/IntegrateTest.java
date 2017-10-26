package ru.zakusov.test.chapter3;

import org.junit.Test;

import java.util.function.DoubleUnaryOperator;

import static org.junit.Assert.assertEquals;

public class IntegrateTest {

    public static final double DELTA = 0.000_000_1;

    @Test
    public void integrate1() throws Exception {
        assertEquals(10.0, integrate(x -> 1, 0, 10), DELTA);
    }

    @Test
    public void integrateXplus1() throws Exception {
        assertEquals(70.0, integrate(x -> x + 2, 0, 10), DELTA);
    }

    @Test
    public void integrateSinXdivX() throws Exception {
        assertEquals(0.603_848_1, integrate(x -> Math.sin(x) / x, 1, 5), DELTA);
    }

    private double integrate(DoubleUnaryOperator f, double a, double b) {
        return Integrate.integrate3(f, a, b);
    }
}