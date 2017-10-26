package ru.zakusov.test.chapter3;

import java.util.function.DoubleUnaryOperator;

/**
 * Реализуйте метод, выполняющий численное интегрирование заданной функции на заданном интервале по формуле левых прямоугольников.
 * <p>
 * Функция задана объектом, реализующим интерфейс java.util.function.DoubleUnaryOperator. Его метод applyAsDouble() принимает значение аргумента и возвращает значение функции в заданной точке.
 * <p>
 * Интервал интегрирования задается его конечными точками a
 * и b, причем a<=b. Для получения достаточно точного результата используйте шаг сетки не больше 10−6.
 */
public class Integrate {

    /**
     * Контракт метода по заданию.
     */
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double result1;
        double result2;

        // Количество шагов
        int n = 1;

        // Точность
        double precision = 0.000_000_1;
        do {
            result1 = integrate(f, a, b, n);
            result2 = integrate(f, a, b, n *= 10);
        } while (Math.abs(result1 - result2) >= precision);
        return result1;
    }

    private static double integrate(DoubleUnaryOperator f, double a, double b, int n) {
        // Шаг сетки
        double h = (b - a) / n;

        double result = 0.0d;
        for (int i = 0; i < n; i++) {
            // Вычисляем в средней точке и добавляем в сумму
            result += f.applyAsDouble(a + h * (i + 0.5));
        }
        return result * h;
    }

    public static double integrate2(DoubleUnaryOperator f, double a, double b) {
        double step = 0.000_000_1;
        double result = 0;
        for (double i = a; i < b; i += step) {
            result += f.applyAsDouble(i);
        }

        return result * step;
    }

    public static double integrate3(DoubleUnaryOperator f, double a, double b) {
        if (b - a < 1e-6) {
            return f.applyAsDouble(a) * (b - a);
        }
        return integrate(f, a, (a + b) / 2) + integrate(f, (a + b) / 2, b);
    }
}
