package ru.zakusov.test.chapter6;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализуйте метод, вычисляющий симметрическую разность двух множеств.
 * <p>
 * Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.
 * <p>
 * Пример:
 * <p>
 * Симметрическая разность множеств {1, 2, 3} и {0, 1, 2} равна {0, 3}.
 */
public class SymmetricDifference {

    /**
     * Контракт метода по заданию.
     */
    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        if (set1 == null || set1.isEmpty()) {
            if (set2 == null) {
                return new HashSet<>();
            }
            return new HashSet<>(set2);
        }
        if (set2 == null || set2.isEmpty()) {
            return new HashSet<>(set1);
        }

        Set<T> result = new HashSet<>();
        set1.stream().filter(item -> !set2.contains(item)).forEach(result::add);
        set2.stream().filter(item -> !set1.contains(item)).forEach(result::add);
        return result;
    }
}
