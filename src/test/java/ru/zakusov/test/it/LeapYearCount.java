package ru.zakusov.test.it;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeapYearCount {

    /**
     * В Григорианском календаре год является високосным в двух случаях: либо он кратен 4, но при этом не кратен 100, либо кратен 400.
     * <p>
     * Реализуйте метод, вычисляющий количество високосных лет с начала нашей эры (первого года) до заданного года включительно.
     * На самом деле Григорианский календарь был введен значительно позже, но здесь для упрощения мы распространяем его действие
     * на всю нашу эру.
     */
    public static int leapYearCount(int year) {
        return year / 4 - year / 100 + year / 400;
    }

    @Test
    public void leapYearCount() {
        assertEquals(0, leapYearCountAndPrint(0));
        assertEquals(0, leapYearCountAndPrint(1));
        assertEquals(0, leapYearCountAndPrint(2));
        assertEquals(0, leapYearCountAndPrint(3));
        assertEquals(1, leapYearCountAndPrint(4));
        assertEquals(1, leapYearCountAndPrint(5));
        assertEquals(1, leapYearCountAndPrint(6));
        assertEquals(1, leapYearCountAndPrint(7));
        assertEquals(2, leapYearCountAndPrint(8));
        assertEquals(2, leapYearCountAndPrint(9));
        assertEquals(99 / 4, leapYearCountAndPrint(99));
        assertEquals(100 / 4 - 1, leapYearCountAndPrint(100));
        assertEquals(104 / 4 - 1, leapYearCountAndPrint(104));
        assertEquals(200 / 4 - 2, leapYearCountAndPrint(200));
        assertEquals(300 / 4 - 3, leapYearCountAndPrint(300));
        assertEquals(400 / 4 - 3, leapYearCountAndPrint(400));
        leapYearCountAndPrint(1900);
        leapYearCountAndPrint(2000);
        leapYearCountAndPrint(2008);
    }

    private static int leapYearCountAndPrint(int year) {
        int result = leapYearCount(year);
        System.out.println(year + " - " + result);
        return result;
    }
}
