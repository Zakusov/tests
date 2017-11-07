package ru.zakusov.test.chapter5;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private Main calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new Main();
    }

    @Test
    public void calculateDecimalsForNumbers() throws Exception {
        assertEquals(6.0, calculateDecimals("1 2 3"), 1.0E-6);
    }

    @Test
    public void calculateDecimalsForString() throws Exception {
        assertEquals(0.0, calculateDecimals("a1 b2 c3"), 1.0E-6);
    }

    @Test
    public void calculateDecimalsForNewLines() throws Exception {
        assertEquals(-981.889, calculateDecimals("-1e3\r\n18 .111 11bbb"), 1.0E-6);
    }

    private double calculateDecimals(String string) throws IOException {
        InputStream in = new ByteArrayInputStream(string.getBytes());
        return calculator.calculateDecimals(in);
    }
}