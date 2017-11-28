package ru.zakusov.test.chapter6.top10;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту появления слов,
 * и в конце выводящую 10 наиболее часто встречающихся слов.
 * <p>
 * Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр.
 * Например, в строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".
 * <p>
 * Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" — это одно и то же слово.
 * Выводите слова в нижнем регистре.
 * <p>
 * Если в тексте меньше 10 уникальных слов, то выводите сколько есть.
 * <p>
 * Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте,
 * то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.
 */
// TODO Your code complexity score is 25.32 (less is better).
public class Main {

    public static void main(String[] args) {
        filterTop10(System.in, System.out);
    }

    public static void filterTop10(InputStream inputStream, PrintStream outputStream) {
        List<String> words = getWords(inputStream);
        Map<String, Long> grouped = words.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), TreeMap::new, Collectors.counting()
                )
        );

        grouped.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEachOrdered(x -> outputStream.println(x.getKey()));
    }

    public static List<String> getWords(InputStream stream) {
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(stream, "UTF-8").useDelimiter("[^\\p{L}\\p{Digit}]+");
        while (scanner.hasNext()) {
            words.add(scanner.next().toLowerCase());
        }
        return words;
    }
}
