package ru.zakusov.test.chapter5;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class CheckSumOfStreamTest {

    @Test
    public void checkSumOfStream() throws Exception {
        byte[] bytes = {0x33, 0x45, 0x01};
        InputStream stream = new ByteArrayInputStream(bytes);
        assertEquals(71, CheckSumOfStream.checkSumOfStream(stream));
    }
}