package ru.zakusov.test.chapter2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Тест {@link MergeArrays}.
 */
public class MergeArraysTest {

    @Test
    public void testMergeArrays() {
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3}, MergeArrays.mergeArrays(new int[]{0, 2, 2}, new int[]{1, 3})));
        assertTrue(Arrays.equals(new int[]{0, 0, 2, 2, 3}, MergeArrays.mergeArrays(new int[]{0, 2, 2}, new int[]{0, 3})));
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3}, MergeArrays.mergeArrays(new int[]{1, 3}, new int[]{0, 2, 2})));
        assertTrue(Arrays.equals(new int[]{0, 1, 2, 2, 3, 3}, MergeArrays.mergeArrays(new int[]{1, 3, 3}, new int[]{0, 2, 2})));
        assertTrue(Arrays.equals(new int[]{0, 1}, MergeArrays.mergeArrays(new int[]{1}, new int[]{0})));
    }

    @Test
    public void testMergeArraysForNotInitializedArguments() {
        assertTrue(Arrays.equals(new int[]{0, 2, 2}, MergeArrays.mergeArrays(new int[]{0, 2, 2}, new int[]{})));
        assertTrue(Arrays.equals(new int[]{0, 2, 2}, MergeArrays.mergeArrays(new int[]{0, 2, 2}, null)));
        assertTrue(Arrays.equals(new int[]{1, 3}, MergeArrays.mergeArrays(new int[]{}, new int[]{1, 3})));
        assertTrue(Arrays.equals(new int[]{1, 3}, MergeArrays.mergeArrays(null, new int[]{1, 3})));
    }
}
