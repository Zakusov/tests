package ru.zakusov.test.chapter2;

/**
 * Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после символа "\" (обратный слэш) на расстоянии a.
 */
public class CharExpression {

    public static char charExpression(int a) {
        return (char) ('\\' + a);
    }
}
