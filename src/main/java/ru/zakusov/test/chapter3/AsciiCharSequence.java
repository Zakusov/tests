package ru.zakusov.test.chapter3;

/**
 * Напишите класс AsciiCharSequence, реализующий компактное хранение последовательности ASCII-символов
 * (их коды влезают в один байт) в массиве байт. По сравнению с классом String, хранящим каждый символ как char,
 * AsciiCharSequence будет занимать в два раза меньше памяти.
 * <p>
 * Класс AsciiCharSequence должен:
 * <p>
 * реализовывать интерфейс java.lang.CharSequence;
 * иметь конструктор, принимающий массив байт;
 * определять методы length(), charAt(), subSequence() и toString()
 * <p>
 * Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса java.lang.CharSequence
 * (JavaDoc или исходники).
 * <p>
 * В данном задании методам charAt() и subSequence() всегда будут подаваться корректные входные параметры,
 * поэтому их проверкой и обработкой ошибок заниматься не нужно.
 */
// FIXME Your code complexity score is 23.43 (less is better).
public class AsciiCharSequence implements CharSequence {

    private final byte[] bytes;

    public AsciiCharSequence(byte[] asciiChars) {
        bytes = asciiChars.clone();
    }

    @Override
    public int length() {
        return bytes == null ? 0 : bytes.length;
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(java.util.Arrays.copyOfRange(bytes, start, end));
    }

    @Override
    public java.util.stream.IntStream chars() {
        return toString().chars();
    }

    @Override
    public java.util.stream.IntStream codePoints() {
        return toString().codePoints();
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AsciiCharSequence) {
            return toString().equals(obj.toString());
        }
        return false;
    }
}
