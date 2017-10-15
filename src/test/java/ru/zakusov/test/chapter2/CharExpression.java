package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharExpression {

    /**
     * Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после символа "\" (обратный слэш) на расстоянии a.
     */
    public static char charExpression(int a) {
        return (char) ('\\' + a);
    }

    @Test
    public void charExpression() {
        assertEquals('|', charExpression(32));
        assertEquals('y', charExpression(29));
    }
}
