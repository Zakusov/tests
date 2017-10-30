package ru.zakusov.test.chapter4;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * В этой задаче вам нужно реализовать метод, настраивающий параметры логирования.
 * Конфигурирование в коде позволяет выполнить более тонкую и хитрую настройку, чем при помощи properties-файла.
 * <p>
 * Требуется выставить такие настройки, чтобы:
 * <p>
 * Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
 * Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
 * Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java",
 * независимо от серьезности сообщения печатались в консоль в формате XML (*)
 * и не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".
 * <p>
 * Не упомянутые здесь настройки изменяться не должны.
 */
public class Logging {

    /**
     * Контракт метода по заданию.
     */
    private static void configureLogging() {
        Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.ALL);
        Logger.getLogger("org.stepic.java.logging.ClassB").setLevel(Level.WARNING);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());

        Logger logger = Logger.getLogger("org.stepic.java");
        logger.setUseParentHandlers(false);
        logger.addHandler(handler);
    }
}
