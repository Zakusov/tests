package ru.zakusov.test.it;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Task02 {

    /**
     * Реализуйте метод, возвращающий true, если среди четырех его аргументов ровно два истинны (любые).
     * Во всех остальных случаях метод должен возвращать false.
     */
    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        int count = 0;
        for (boolean x : new boolean[]{a, b, c, d}) {
            if (x && (++count > 2)) {
                return false;
            }
        }
        return count == 2;
    }

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
        boolean result = booleanExpression(a == '1', b == '1', c == '1', d == '1');
        System.out.println("" + a + b + c + d + " -> " + result);
        return result;
    }
}