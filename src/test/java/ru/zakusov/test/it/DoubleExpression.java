package ru.zakusov.test.it;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoubleExpression {

    /**
     * Реализуйте метод, возвращающий ответ на вопрос: правда ли, что a + b = c?
     * <p>
     * Допустимая погрешность – 0.0001 (1E-4)
     */
    public static boolean doubleExpression(double a, double b, double c) {
        return Math.abs(c - b - a) <= 1E-4;
    }

    @Test
    public void doubleExpression() {
        assertTrue(doubleExpressionAndPrint(0.1, 0.2, 0.3));
        assertTrue(doubleExpressionAndPrint(0.3, -0.2, 0.1));
        assertTrue(doubleExpressionAndPrint(-0.3, 0.4, 0.1));
        assertFalse(doubleExpressionAndPrint(0.1, 0.2, 0.2));
        assertTrue(doubleExpressionAndPrint(0.001, 0.002, 0.003));
        assertTrue(doubleExpressionAndPrint(0.0011, 0.0022, 0.0033));
        assertFalse(doubleExpressionAndPrint(0.0011, 0.0022, 0.0030));
        assertTrue(doubleExpressionAndPrint(0.003, -0.002, 0.001));
        assertTrue(doubleExpressionAndPrint(-0.003, 0.004, 0.001));
        assertFalse(doubleExpressionAndPrint(0.001, 0.002, 0.002));
        assertTrue(doubleExpressionAndPrint(0.0001, 0.0002, 0.0003));
        assertTrue(doubleExpressionAndPrint(0.00011, 0.00022, 0.00033));
        assertTrue(doubleExpressionAndPrint(0.00011, 0.00022, 0.00030));
        assertTrue(doubleExpressionAndPrint(0.0003, -0.0002, 0.0001));
        assertTrue(doubleExpressionAndPrint(-0.0003, 0.0004, 0.0001));
        assertTrue(doubleExpressionAndPrint(0.0001, 0.0002, 0.0002));
        assertTrue(doubleExpressionAndPrint(0.00001, 0.00002, 0.00003));
        assertTrue(doubleExpressionAndPrint(0.00001, 0.00002, 0.00002));
        assertTrue(doubleExpressionAndPrint(0.0000000001, 0.0000000002, 0.0000000003));
        assertTrue(doubleExpressionAndPrint(0.0000000001, 0.0000000002, 0.0000000002));
    }

    private static boolean doubleExpressionAndPrint(double a, double b, double c) {
        boolean result = doubleExpression(a, b, c);
        System.out.println(a + " + " + b + " = " + c + " -> " + result);
        return result;
    }
}
