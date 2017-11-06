package ru.zakusov.test.chapter4.uxwriter;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class MainTest {

    @Test
    public void testSystemOutUnixWriter() throws Exception {
        byte[] incoming = {65, 13, 10, 10, 13};
        byte[] expected = {65, 10, 10, 13};
        InputStream in = new ByteArrayInputStream(incoming);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        new Main().toUnixStyleEOL(in, out);

        byte[] result = outputStream.toByteArray();
        System.out.println(Arrays.toString(result));
        assertTrue(Arrays.equals(expected, result));
    }
}