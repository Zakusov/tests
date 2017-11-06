package ru.zakusov.test.chapter5;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Реализуйте метод, который зачитает данные из InputStream и преобразует их в строку,
 * используя заданную кодировку.
 */
public class StringReader {

    /**
     * Контракт метода по заданию.
     */
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (InputStreamReader in = new InputStreamReader(inputStream, charset)) {
            int value;
            while ((value = in.read()) != -1) {
                buffer.append((char) value);
            }
        }
        return buffer.toString();
    }
}
