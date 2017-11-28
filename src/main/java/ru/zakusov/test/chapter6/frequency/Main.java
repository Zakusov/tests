package ru.zakusov.test.chapter6.frequency;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
public class Main {

    public static void main(String[] args) {
        filterTop10(System.in, System.out);
    }

    public static void filterTop10(InputStream inputStream, PrintStream outputStream) {
        Map<String, Integer> counter = getCounter(inputStream);
        if (counter.size() != 0) {
            final int[] printed = {0};
            List<Integer> top10 = getTop10(counter);
            counter.forEach((word, count) -> {
                if (top10.contains(count)) {
                    outputStream.println(word);
                    if (++printed[0] > 9) {
                        return;
                    }
                }
            });
        }
    }

    public static Map<String, Integer> getCounter(InputStream inputStream) {
        Map<String, Integer> counter = new TreeMap<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            int count = 1;
            String word = scanner.next().toLowerCase();
            if (counter.containsKey(word)) {
                count = counter.get(word).intValue() + 1;
            }
            counter.put(word, count);
        }
        return counter;
    }

    public static List<Integer> getTop10(Map<String, Integer> counter) {
        Stream<Integer> sorted = counter.values().stream().sorted(Comparator.reverseOrder());
        IntStream limited = sorted.mapToInt(i -> i).limit(10);
        return limited.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
