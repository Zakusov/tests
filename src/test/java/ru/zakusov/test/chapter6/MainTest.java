package ru.zakusov.test.chapter6;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void skipEvenIntegers() throws Exception {
        InputStream in = new ByteArrayInputStream("1 2 3 4 5 6 7".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (PrintStream printStream = new PrintStream(out)) {
            Main.skipEvenIntegers(in, printStream);
            assertEquals("6 4 2", out.toString());
        }
    }
}