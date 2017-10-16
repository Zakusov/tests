package ru.zakusov.test.chapter2;

import java.math.BigInteger;

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
}
