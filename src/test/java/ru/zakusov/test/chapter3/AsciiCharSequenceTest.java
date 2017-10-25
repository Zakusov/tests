package ru.zakusov.test.chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsciiCharSequenceTest {

    private AsciiCharSequence ascii;

    @Before
    public void setUp() throws Exception {
        ascii = new AsciiCharSequence(new byte[]{'H', 'e', 'l', 'l', 'o'});
    }

    @Test
    public void length() throws Exception {
        assertEquals(5, ascii.length());
    }

    @Test
    public void charAt() throws Exception {
        assertEquals('H', ascii.charAt(0));
        assertEquals('e', ascii.charAt(1));
        assertEquals('l', ascii.charAt(2));
        assertEquals('l', ascii.charAt(3));
        assertEquals('o', ascii.charAt(4));
    }

    @Test
    public void subSequence() throws Exception {
        AsciiCharSequence expected = new AsciiCharSequence(new byte[]{'e', 'l', 'l'});
        assertEquals(expected, ascii.subSequence(1, 4));
    }

    @Test
    public void chars() throws Exception {
        assertEquals(5, ascii.chars().count());
    }

    @Test
    public void codePoints() throws Exception {
        assertEquals(5, ascii.codePoints().count());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Hello", ascii.toString());
    }
}