package ru.zakusov.test.chapter6;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class TernaryOperatorTest {

    private Function<String, Integer> safeStringLength;

    @Before
    public void setUp() throws Exception {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        safeStringLength = TernaryOperator.ternaryOperator(condition, ifTrue, ifFalse);
    }

    @Test
    public void testForNotNullString() throws Exception {
        assertEquals(3, safeStringLength.apply("abc").intValue());
    }

    @Test
    public void testForNull() throws Exception {
        assertEquals(0, safeStringLength.apply(null).intValue());
    }
}