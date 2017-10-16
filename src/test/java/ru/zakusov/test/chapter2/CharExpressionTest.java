package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест {@link CharExpression}.
 */
public class CharExpressionTest {

    @Test
    public void charExpression() {
        assertEquals('|', CharExpression.charExpression(32));
        assertEquals('y', CharExpression.charExpression(29));
    }
}
