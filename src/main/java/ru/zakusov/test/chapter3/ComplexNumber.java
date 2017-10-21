package ru.zakusov.test.chapter3;

/**
 * Дан класс ComplexNumber. Переопределите в нем методы equals() и hashCode() так,
 * чтобы equals() сравнивал экземпляры ComplexNumber по содержимому полей re и im,
 * а hashCode() был бы согласованным с реализацией equals().
 * <p>
 * Реализация hashCode(), возвращающая константу или не учитывающая дробную часть re и im, засчитана не будет.
 */
// FIXME: RESULT 1: Your code complexity score is 15.17 (best for this step is 9.0).
// RESULT 2 (current): Your code complexity score is 14.32 (less is better).
public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(re + im);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ComplexNumber) {
            ComplexNumber number = (ComplexNumber) obj;
            return Double.compare(number.re, re) == 0
                    && Double.compare(number.im, im) == 0;
        }
        return false;
    }
}