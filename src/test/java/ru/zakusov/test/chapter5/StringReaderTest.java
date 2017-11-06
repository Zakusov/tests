package ru.zakusov.test.chapter5;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class StringReaderTest {

    @Test
    public void readAsString() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{48, 49, 50, 51});
        assertEquals("0123", StringReader.readAsString(in, StandardCharsets.US_ASCII));
    }
}