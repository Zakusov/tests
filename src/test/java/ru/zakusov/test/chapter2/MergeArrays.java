package ru.zakusov.test.chapter2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Реализуйте метод, сливающий два отсортированных по неубыванию массива чисел в один отсортированный в том же порядке массив.
 * Массивы могут быть любой длины, в том числе нулевой.
 * <p>
 * Предполагается, что вы реализуете алгоритм слияния, имеющий линейную сложность: он будет идти по двум исходным массивам
 * и сразу формировать отсортированный результирующий массив. Так, чтобы сортировка полученного массива при помощи Arrays.sort()
 * уже не требовалась.
 */
public class MergeArrays {
    /**
     * Merges two given sorted arrays into one
     *
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return new array containing all elements from a1 and a2, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        if (a1 == null || a1.length == 0) {
            if (a2 == null || a2.length == 0) {
                return new int[0];
            }
            return a2;
        }
        if (a2 == null || a2.length == 0) {
            return a1;
        }

        // Теперь работаем с массивами, где есть хотя бы 1 элемент.
        int[] a3 = new int[a1.length + a2.length];
        for (int index1 = 0, index2 = 0, index3 = 0; index3 < a3.length; ) {
            int minValue = a1[index1] < a2[index2] ? a1[index1] : a2[index2];
            // Работаем с первым массивом
            while (index1 < a1.length && minValue == a1[index1]) {
                a3[index3++] = a1[index1++];
            }
            if (index1 == a1.length) {
                // Первый массив закончился, сливаем хвост второго
                while (index2 < a2.length && index3 < a3.length) {
                    a3[index3++] = a2[index2++];
                }
                return a3;
            }
            // Работаем со вторым массивом
            while (index2 < a2.length && minValue == a2[index2]) {
                a3[index3++] = a2[index2++];
            }
            if (index2 == a2.length) {
                // Второй массив закончился, сливаем хвост первого массива
                while (index1 < a1.length && index3 < a3.length) {
                    a3[index3++] = a1[index1++];
                }
                return a3;
            }
        }
        return a3;
    }

    @Test
    public void testMergeArrays() {
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3}, mergeArrays(new int[]{0, 2, 2}, new int[]{1, 3})));
        assertTrue(Arrays.equals(new int[]{0, 0, 2, 2, 3}, mergeArrays(new int[]{0, 2, 2}, new int[]{0, 3})));
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3}, mergeArrays(new int[]{1, 3}, new int[]{0, 2, 2})));
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3, 3}, mergeArrays(new int[]{1, 3, 3}, new int[]{0, 2, 2})));
        assertTrue(Arrays.equals(new int[]{0, 1}, mergeArrays(new int[]{1}, new int[]{0})));
    }

    @Test
    public void testMergeArraysForNotInitializedArguments() {
        assertTrue(Arrays.equals(new int[]{0, 2, 2}, mergeArrays(new int[]{0, 2, 2}, new int[]{})));
        assertTrue(Arrays.equals(new int[]{0, 2, 2}, mergeArrays(new int[]{0, 2, 2}, null)));
        assertTrue(Arrays.equals(new int[]{1, 3}, mergeArrays(new int[]{}, new int[]{1, 3})));
        assertTrue(Arrays.equals(new int[]{1, 3}, mergeArrays(null, new int[]{1, 3})));
    }
}
