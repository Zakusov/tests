package ru.zakusov.test.chapter6;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SymmetricDifferenceTest {
    @Test
    public void symmetricDifference() throws Exception {
        Set<Integer> set1 = toSet(new Integer[]{1, 2, 3});
        Set<Integer> set2 = toSet(new Integer[]{0, 1, 2});
        Set<Integer> expected = toSet(new Integer[]{0, 3});
        assertTrue(expected.equals(SymmetricDifference.symmetricDifference(set1, set2)));

        Set<Integer> notExpected = toSet(new Integer[]{0, 2});
        assertFalse(notExpected.equals(SymmetricDifference.symmetricDifference(set1, set2)));
    }

    @Test
    public void symmetricDifferenceForExceptions() throws Exception {
        Set<Integer> set1 = toSet(new Integer[]{1, 2, 3});
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> expected = toSet(new Integer[]{1, 2, 3});
        assertTrue(expected.equals(SymmetricDifference.symmetricDifference(set1, null)));
        assertTrue(expected.equals(SymmetricDifference.symmetricDifference(set1, set2)));
        assertTrue(expected.equals(SymmetricDifference.symmetricDifference(null, set1)));
        assertTrue(expected.equals(SymmetricDifference.symmetricDifference(set2, set1)));

        Set<Integer> notExpected = toSet(new Integer[]{0, 2});
        assertFalse(notExpected.equals(SymmetricDifference.symmetricDifference(set1, set2)));
    }

    private <T> HashSet<T> toSet(T[] integers) {
        return new HashSet<>(Arrays.asList(integers));
    }
}