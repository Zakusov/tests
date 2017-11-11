package ru.zakusov.test.chapter6;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Напишите программу, которая прочитает из System.in последовательность целых чисел,
 * разделенных пробелами, затем удалит из них все числа, стоящие на четных позициях,
 * и затем выведет получившуюся последовательность в обратном порядке в System.out.
 * <p>
 * Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.
 */
// FIXME Your code complexity score is 20.25 (less is better).
public class Main {

    public static void main(String[] args) {
        skipEvenIntegers(System.in, System.out);
    }

    public static void skipEvenIntegers(InputStream in, PrintStream out) {
        Deque deque = getOddIntegers(in);
        if (!deque.isEmpty()) {
            out.print(deque.pollLast());
        }
        while (!deque.isEmpty()) {
            out.print(" " + deque.pollLast());
        }
    }

    private static Deque getOddIntegers(InputStream in) {
        Deque deque = new ArrayDeque();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextInt()) {
            scanner.nextInt();
            if (scanner.hasNextInt()) {
                deque.add(scanner.nextInt());
            }
        }
        return deque;
    }
}
