package ru.zakusov.test.it;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    /**
     * Реализуйте метод, проверяющий, является ли заданная строка палиндромом.
     * Палиндромом называется строка, которая читается одинаково слева направо и справа налево (в том числе пустая).
     * При определении "палиндромности" строки должны учитываться только буквы и цифры.
     * А пробелы, знаки препинания, а также регистр символов должны игнорироваться.
     * Гарантируется, что в метод попадают только строки, состоящие из символов ASCII (цифры, латинские буквы, знаки препинания).
     * Т.е. русских, китайских и прочих экзотических символов в строке не будет.
     */
    @Test
    public void isPalindrome() {
        assertFalse(isPalindrome(null));
        assertTrue(isPalindrome(""));
        assertTrue(isPalindrome("   "));
        assertTrue(isPalindrome("abba"));
        assertTrue(isPalindrome("abba   "));
        assertTrue(isPalindrome("abBA"));
        assertTrue(isPalindrome("ab-ba"));
        assertTrue(isPalindrome("abcba"));
        assertFalse(isPalindrome("abcdf"));
        assertTrue(isPalindrome("Madam, I'm Adam!"));
        assertTrue(isPalindrome("А роза упала на лапу Азора."));
    }
}
