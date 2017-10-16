package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест {@link Palindrome}.
 */
public class PalindromeTest {

    @Test
    public void isPalindrome() {
        assertFalse(Palindrome.isPalindrome(null));
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome("   "));
        assertTrue(Palindrome.isPalindrome("abba"));
        assertTrue(Palindrome.isPalindrome("abba   "));
        assertTrue(Palindrome.isPalindrome("abBA"));
        assertTrue(Palindrome.isPalindrome("ab-ba"));
        assertTrue(Palindrome.isPalindrome("abcba"));
        assertFalse(Palindrome.isPalindrome("abcdf"));
        assertTrue(Palindrome.isPalindrome("Madam, I'm Adam!"));
        assertTrue(Palindrome.isPalindrome("А роза упала на лапу Азора."));
    }
}
