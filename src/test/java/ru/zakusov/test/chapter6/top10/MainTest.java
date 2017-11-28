package ru.zakusov.test.chapter6.top10;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void getWords() throws Exception {
        InputStream inputStream = toInputStream("Мама мыла-мыла-мыла раму!");
        List<String> counter = Main.getWords(inputStream);
        System.out.println(counter);
        assertEquals(5, counter.size());
        assertEquals("мама", counter.get(0));
        assertEquals("мыла", counter.get(1));
        assertEquals("мыла", counter.get(2));
        assertEquals("мыла", counter.get(3));
        assertEquals("раму", counter.get(4));
    }

    @Test
    public void filterTop10() throws Exception {
        InputStream inputStream = toInputStream("Мама мыла-мыла-мыла раму!");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        Main.filterTop10(inputStream, out);

        assertEquals("мыла" + System.lineSeparator()
                        + "мама" + System.lineSeparator()
                        + "раму" + System.lineSeparator(),
                new String(outputStream.toByteArray()));
    }

    @Test
    public void filterTop10forBook() throws Exception {
        InputStream inputStream = toInputStream("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. "
                + "Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. "
                + "Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Integer vel odio nec mi tempor dignissim.");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        Main.filterTop10(inputStream, out);

        assertEquals("consectetur" + System.lineSeparator()
                        + "faucibus" + System.lineSeparator()
                        + "ipsum" + System.lineSeparator()
                        + "lorem" + System.lineSeparator()
                        + "adipiscing" + System.lineSeparator()
                        + "amet" + System.lineSeparator()
                        + "dolor" + System.lineSeparator()
                        + "eget" + System.lineSeparator()
                        + "elit" + System.lineSeparator()
                        + "mi" + System.lineSeparator(),
                new String(outputStream.toByteArray()));
    }

    private InputStream toInputStream(String source) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8.name()));
    }
}