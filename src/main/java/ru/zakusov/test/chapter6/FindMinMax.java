package ru.zakusov.test.chapter6;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.
 */
public class FindMinMax {

    /**
     * Контракт метода по заданию.
     */
    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {

        Object[] minMax = new Object[2];
        stream.forEach(x -> {
            if (minMax[0] == null || order.compare(x, (T) minMax[0]) < 0) {
                minMax[0] = x;
            }
            if (minMax[1] == null || order.compare(x, (T) minMax[1]) > 0) {
                minMax[1] = x;
            }
        });
        minMaxConsumer.accept((T) minMax[0], (T) minMax[1]);
    }
}
