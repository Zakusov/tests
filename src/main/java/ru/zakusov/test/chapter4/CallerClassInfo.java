package ru.zakusov.test.chapter4;

/**
 * Реализуйте метод, позволяющий другим методам узнать, откуда их вызвали.
 * <p>
 * Метод getCallerClassAndMethodName() должен возвращать имя класса и метода,
 * откуда вызван метод, вызвавший данный утилитный метод. Или null (нулевую ссылку, а не строку "null"),
 * если метод, вызвавший getCallerClassAndMethodName() является точкой входа в программу, т.е. его никто не вызывал.
 */
public class CallerClassInfo {

    /**
     * Контракт метода по заданию.
     */
    public static String getCallerClassAndMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length > 3) {
            StackTraceElement caller = stackTrace[3];
            return caller.getClassName() + "#" + caller.getMethodName();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getCallerClassAndMethodName());
        anotherMethod();
    }

    private static void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }
}
