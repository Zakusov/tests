package ru.zakusov.test.chapter5;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Реализуйте метод, который зачитает данные из InputStream и преобразует их в строку,
 * используя заданную кодировку.
 */
// FIXME Failed. Test 6. Expected IOException not thrown
public class StringReader {

    /**
     * Контракт метода по заданию.
     */
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(new InputStreamReader(inputStream, charset)).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
