package ru.zakusov.test.chapter6.frequency;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Top10Test {

    @Test
    public void test() throws Exception {
        InputStream inputStream = toInputStream("Мама мыла-мыла-мыла раму!");
        Map<String, Integer> counter = Main.getCounter(inputStream);
        assertEquals(3, counter.size());
        assertEquals(1, counter.get("мама").intValue());
        assertEquals(3, counter.get("мыла").intValue());
        assertEquals(1, counter.get("раму").intValue());
    }

    private InputStream toInputStream(String source) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8.name()));
    }
}