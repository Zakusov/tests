package ru.zakusov.test.chapter5;

import java.io.IOException;
import java.io.InputStream;

/**
 * Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.
 * <p>
 * Контрольная сумма данных вычисляется по следующему алгоритму:
 * <p>
 * Контрольная сумма представляет собой число типа int. Контрольная сумма пустого набора данных равна нулю.
 * Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле: Cn+1=rotateLeft(Cn) xor bn+1,
 * где Cn — контрольная сумма первых n байт данных, rotateLeft — циклический сдвиг бит числа на один бит влево
 * (чтобы не изобретать велосипед, используйте Integer.rotateLeft), bn  — n-ный байт данных.
 * <p>
 * Поскольку метод не открывал данный InputStream, то и закрывать его он не должен.
 * Выброшенное из методов InputStream исключение должно выбрасываться из метода.
 */
public class CheckSumOfStream {

    /**
     * Контракт метода по заданию.
     */
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int value, checksum = 0;
        while ((value = inputStream.read()) != -1) {
            checksum = Integer.rotateLeft(checksum, 1) ^ value;
        }
        return checksum;
    }
}
