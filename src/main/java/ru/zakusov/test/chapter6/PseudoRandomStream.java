package ru.zakusov.test.chapter6;

import java.util.stream.IntStream;

/**
 * Напишите метод, возвращающий стрим псевдослучайных целых чисел. Алгоритм генерации чисел следующий:
 * <p>
 * Берется какое-то начальное неотрицательное число (оно будет передаваться в ваш метод проверяющей системой).
 * Первый элемент последовательности устанавливается равным этому числу.
 * Следующие элементы вычисляются по рекуррентной формуле Rn+1=mid(R2n),
 * <p>
 * где mid — это функция, выделяющая второй, третий и четвертый разряд переданного числа. Например, mid(123456)=345.
 */
public class PseudoRandomStream {

    /**
     * Контракт метода по заданию.
     */
    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, x -> mid(x * x));
    }

    /**
     * Выделяет второй, третий и четвертый разряд переданного числа. Например, mid(123456)=345.
     *
     * @param x число для разбора.
     * @return результат разбора.
     */
    static int mid(int x) {
        return x / 10 % 1000;
    }
}