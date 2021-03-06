package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест {@link BooleanExpression}.
 */
public class BooleanExpressionTest {

    @Test
    public void booleanExpression() {
        for (int i = 0; i < 16; i++) {
            String str = toBinaryString(i, 4);
            boolean result = booleanExpression(str.charAt(0), str.charAt(1), str.charAt(2), str.charAt(3));
            assertEquals(result, Integer.bitCount(i) == 2);
        }
    }

    private String toBinaryString(int number, int length) {
        String str = Integer.toBinaryString(1 << length) + Integer.toBinaryString(number);
        return str.substring(str.length() - length);
    }

    private static boolean booleanExpression(char a, char b, char c, char d) {
        boolean result = BooleanExpression.booleanExpression(a == '1', b == '1', c == '1', d == '1');
        System.out.println("" + a + b + c + d + " -> " + result);
        return result;
    }
}