package ru.zakusov.test.chapter4.uxwriter;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Напишите программу, которая будет преобразовывать переводы строк из формата Windows в формат Unix.
 * Данные в формате Windows подаются программе в System.in, преобразованные данные должны выводиться в System.out.
 * На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем Main — таково ограничение проверяющей системы),
 * метод main, прописать все import'ы.
 * <p>
 * Требуется заменить все вхождения пары символов '\r' и '\n' на один символ '\n'.
 * Если на входе встречается одиночный символ '\r', за которым не следует '\n', то символ '\r' выводится без изменения.
 */
// FIXME Your code complexity score is 18.49 (best for this step is 7.81).
public class Main {

    public static void main(String[] args) throws Exception {
        new Main().toUnixStyleEOL(System.in, System.out);
    }

    public void toUnixStyleEOL(InputStream in, PrintStream out) throws Exception {
        try (UnixStyleWriter writer = new UnixStyleWriter(out)) {
            int value;
            while ((value = in.read()) != -1) {
                writer.write(value);
            }
        }
    }

    protected static class UnixStyleWriter implements AutoCloseable {

        public static final int LINE_FEED = '\n';
        public static final int CARRIAGE_RETURN = '\r';

        private PrintStream out;
        private boolean carriageReturnFound = false;

        public UnixStyleWriter(PrintStream stream) {
            out = stream;
        }

        public void write(int value) {
            if (value == CARRIAGE_RETURN) {
                if (carriageReturnFound) {
                    // Прилетел дубликат \r. Выводим предыдущий.
                    out.write(CARRIAGE_RETURN);
                }
                carriageReturnFound = true;
            } else {
                if (carriageReturnFound && value != LINE_FEED) {
                    // Если прилетел не \n, то выводим \r.
                    out.write(CARRIAGE_RETURN);
                }
                carriageReturnFound = false;
                out.write(value);
            }
        }

        @Override
        public void close() throws Exception {
            if (carriageReturnFound) {
                // Последний символ - \r.
                out.write(CARRIAGE_RETURN);
            }
            out.flush();
        }
    }
}
