package ru.zakusov.test.chapter6;

import java.util.Objects;

/**
 * Реализуйте generic-класс Pair, похожий на Optional, но содержащий пару элементов разных типов
 * и не запрещающий элементам принимать значение null.
 * <p>
 * Реализуйте методы getFirst(), getSecond(), equals() и hashCode(), а также статический фабричный метод of().
 * Конструктор должен быть закрытым (private).
 * <p>
 * Не меняйте модификатор доступа класса Pair. Для корректной проверки класс должен иметь пакетную видимость.
 *
 * @param <T> первый элемент.
 * @param <U> второй элемент.
 */
// TODO Your code complexity score is 15.17 (best for this step is 9.9).
class Pair<T, U> {

    private final T first;

    private final U second;

    private Pair(T value1, U value2) {
        first = value1;
        second = value2;
    }

    public static <T, U> Pair<T, U> of(T value1, U value2) {
        return new Pair<>(value1, value2);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
