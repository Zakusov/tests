package ru.zakusov.test.chapter6;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void ofForNumbers() throws Exception {
        assertNotNull(Pair.of(1, 2));
    }

    @Test
    public void ofForNull() throws Exception {
        assertNotNull(Pair.of(null, null));
    }

    @Test
    public void getFirst() throws Exception {
        assertEquals("a", Pair.of("a", "b").getFirst());
    }

    @Test
    public void getFirstForNull() throws Exception {
        assertNull(Pair.of(null, "b").getFirst());
        assertNotNull(Pair.of(null, "b").getSecond());
    }

    @Test
    public void getSecond() throws Exception {
        assertEquals("b", Pair.of("a", "b").getSecond());
    }

    @Test
    public void getSecondForNull() throws Exception {
        assertNull(Pair.of("a", null).getSecond());
        assertNotNull(Pair.of("a", null).getFirst());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(Pair.of("a", "a").equals(Pair.of("a", "a")));
        assertTrue(Pair.of(null, "a").equals(Pair.of(null, "a")));
        assertTrue(Pair.of("a", null).equals(Pair.of("a", null)));
        assertTrue(Pair.of(null, null).equals(Pair.of(null, null)));

        assertFalse(Pair.of("a", "a").equals(Pair.of("a", "b")));
        assertFalse(Pair.of("a", "a").equals(Pair.of("b", "a")));
        assertFalse(Pair.of("a", "a").equals(Pair.of("b", "b")));
        assertFalse(Pair.of("a", "a").equals("abc"));
        assertFalse(Pair.of("a", "a").equals(null));
    }

    @Test
    public void testHashCode() throws Exception {
        assertTrue(Pair.of("a", "b").hashCode() > 0);
        assertTrue(Pair.of(null, "b").hashCode() > 0);
        assertTrue(Pair.of("a", null).hashCode() > 0);
        assertTrue(Pair.of(null, null).hashCode() > 0);
    }

    @Test
    public void proposedTest() {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        assertEquals(Integer.valueOf(1), pair.getFirst());
        assertEquals("hello", pair.getSecond());

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        assertTrue(pair.equals(pair2));
        assertEquals(pair.hashCode(), pair2.hashCode());
    }
}