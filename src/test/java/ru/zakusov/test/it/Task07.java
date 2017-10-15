package ru.zakusov.test.it;

import org.junit.Test;

public class Task07 {

    @Test
    public void typeConversion() {
        float floatVar = 0.0f;
        long longVar = (long) floatVar;
        int intVar = 0;
        longVar = intVar;
        char charVar = 'a';
        Character charObj = charVar;
        intVar = charVar;
        floatVar = longVar;
        double doubleVar = longVar;
    }
}
