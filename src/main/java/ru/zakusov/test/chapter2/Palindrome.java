package ru.zakusov.test.chapter2;

/**
 * Реализуйте метод, проверяющий, является ли заданная строка палиндромом.
 * Палиндромом называется строка, которая читается одинаково слева направо и справа налево (в том числе пустая).
 * При определении "палиндромности" строки должны учитываться только буквы и цифры.
 * А пробелы, знаки препинания, а также регистр символов должны игнорироваться.
 * Гарантируется, что в метод попадают только строки, состоящие из символов ASCII (цифры, латинские буквы, знаки препинания).
 * Т.е. русских, китайских и прочих экзотических символов в строке не будет.
 */
public class Palindrome {

    /**
     * Checks if given <code>text</code> is a palindrome.
     *
     * @param text any string
     * @return <code>true</code> when <code>text</code> is a palindrome, <code>false</code> otherwise
     */
    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }
        String prepared = text.replaceAll("\\W", "").toLowerCase();
        if (prepared.length() == 0) {
            return true;
        }
        return new StringBuilder(prepared).reverse().toString().equals(prepared);
    }
}
