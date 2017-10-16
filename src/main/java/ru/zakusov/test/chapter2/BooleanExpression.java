package ru.zakusov.test.chapter2;

/**
 * Реализуйте метод, возвращающий true, если среди четырех его аргументов ровно два истинны (любые).
 * Во всех остальных случаях метод должен возвращать false.
 */
public class BooleanExpression {

    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        int count = 0;
        for (boolean x : new boolean[]{a, b, c, d}) {
            if (x && (++count > 2)) {
                return false;
            }
        }
        return count == 2;
    }
}
