package ru.zakusov.test.chapter6;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Дан предикат condition и две функции ifTrue и ifFalse.
 * Напишите метод ternaryOperator, который из них построит новую функцию, возвращающую значение функции ifTrue,
 * если предикат выполнен, и значение ifFalse иначе.
 */
public class TernaryOperator {

    /**
     * Контракт метода по заданию.
     */
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        return value -> (condition.test(value)) ? ifTrue.apply(value) : ifFalse.apply(value);
    }
}
