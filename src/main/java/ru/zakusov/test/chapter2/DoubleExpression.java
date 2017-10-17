package ru.zakusov.test.chapter2;

/**
 * Реализуйте метод, возвращающий ответ на вопрос: правда ли, что a + b = c?
 * <p>
 * Допустимая погрешность – 0.0001 (1E-4)
 */
public class DoubleExpression {

    /**
     * Контракт метода по заданию.
     */
    public static boolean doubleExpression(double a, double b, double c) {
        return Math.abs(c - b - a) <= 1E-4;
    }
}
