package ru.zakusov.test.chapter3;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ComplexNumberTest {

    private Stream<ComplexNumber> numbers;

    @Before
    public void setUp() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber c = new ComplexNumber(1, 1);
        ComplexNumber d = new ComplexNumber(1, 2);
        ComplexNumber e = new ComplexNumber(0, -1);
        numbers = Stream.of(a, b, c, d, e);
    }

    @Test
    public void testEqualsForReflexive() throws Exception {
        numbers.forEach(this::testEqualsForReflexive);
    }

    @Test
    public void testEqualsForSymmetric() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        numbers.forEach(number -> testEqualsForSymmetric(a, number));
    }

    @Test
    public void testEqualsForConsistent() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        numbers.forEach(number -> testEqualsForConsistent(a, number));
    }

    @Test
    public void testEqualsForNull() throws Exception {
        numbers.forEach(this::testEqualsForNull);
    }

    @Test
    public void testHashcodeForEquals() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        numbers.forEach(number -> testHashcodeForEquals(a, number));
    }

    @Test
    public void testHashcodeForNotEquals() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        numbers.forEach(number -> testHashcodeForNotEquals(a, number));
    }

    @Test
    public void textTransitive() throws Exception {
        ComplexNumber a = new ComplexNumber(1, 1);
        ComplexNumber b = new ComplexNumber(1, 1);
        ComplexNumber c = new ComplexNumber(1, 1);
        textTransitive(a, b, c);
    }

    private void testEqualsForReflexive(Object x) {
        // It is reflexive: for any non-null reference value x, x.equals(x) should return true.
        assertTrue(x.equals(x));
    }

    private void testEqualsForSymmetric(Object x, Object y) {
        // It is symmetric: for any non-null reference values x and y, x.equals(y)
        // should return true if and only if y.equals(x) returns true.
        if (x.equals(y)) {
            assertTrue(y.equals(x));
        } else {
            assertFalse(y.equals(x));
        }
    }

    private void testEqualsForConsistent(Object x, Object y) {
        // It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y)
        // consistently return true or consistently return false, provided no information used in equals comparisons
        // on the objects is modified.
        boolean firstResult = x.equals(y);
        IntStream.range(0, 10).forEach(i -> assertEquals(firstResult, x.equals(y)));
    }

    private void testEqualsForNull(Object x) {
        // For any non-null reference value x, x.equals(null) should return false.
        assertFalse(x.equals(null));
    }

    private void textTransitive(Object x, Object y, Object z) {
        // It is transitive: for any non-null reference values x, y, and z,
        // if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
        assertTrue(x.equals(y));
        assertTrue(y.equals(z));
        assertTrue(x.equals(z));
    }

    private void testHashcodeForEquals(Object x, Object y) {
        // If two objects are equal according to the equals(Object) method, then calling the hashCode method
        // on each of the two objects must produce the same integer result.
        if (x.equals(y)) {
            assertTrue(x.hashCode() == y.hashCode());
        }
    }

    private void testHashcodeForNotEquals(Object x, Object y) {
        // It is not required that if two objects are unequal according to the equals(Object) method,
        // then calling the hashCode method on each of the two objects must produce distinct integer results.
        // However, the programmer should be aware that producing distinct integer results for unequal objects
        // may improve the performance of hash tables.
        if (!x.equals(y)) {
            assertTrue(x.hashCode() != y.hashCode());
        }
    }

    // Plus for hashCode:
    // Whenever it is invoked on the same object more than once during an execution of a Java application,
    // the hashCode method must consistently return the same integer, provided no information
    // used in equals comparisons on the object is modified. This integer need not remain consistent
    // from one execution of an application to another execution of the same application.
}