package ru.zakusov.test.chapter5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Напишите программу, читающую текст из System.in и выводящую в System.out сумму всех встреченных
 * в тексте вещественных чисел с точностью до шестого знака после запятой.
 * Числом считается последовательность символов, отделенная от окружающего текста пробелами
 * или переводами строк и успешно разбираемая методом Double.parseDouble.
 * <p>
 * На этот раз вам надо написать программу полностью, т.е. объявить класс
 * (с именем Main — таково ограничение проверяющей системы), метод main, прописать все import'ы.
 */
// FIXME Your code complexity score is 10.3 (best for this step is 7.0).
public class Main {

    public static void main(String[] args) throws IOException {
        double sum = new Main().calculateDecimals(System.in);
        System.out.printf("%.6f", sum);
    }

    /**
     * Контракт метода по заданию.
     */
    public double calculateDecimals(InputStream inputStream) throws IOException {
        double sum = 0.0d;
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNext()) {
                try {
                    sum += Double.parseDouble(scanner.next());
                } catch (NumberFormatException e) {
                    // Just ignore it
                }
            }
        }
        return sum;
    }
}
