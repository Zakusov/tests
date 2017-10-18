package ru.zakusov.test.chapter2;

import java.util.*;

/**
 * Вам дан список ролей и сценарий пьесы в виде массива строчек.
 * <p>
 * Каждая строчка сценария пьесы дана в следующем виде:
 * Роль: текст
 * <p>
 * Текст может содержать любые символы.
 * <p>
 * Напишите метод, который будет группировать строчки по ролям, пронумеровывать их и возвращать результат в виде готового текста (см. пример). Каждая группа распечатывается в следующем виде:
 * <p>
 * Роль:
 * i) текст
 * j) текст2
 * ...
 * ==перевод строки==
 * <p>
 * i и j -- номера строк в сценарии. Индексация строчек начинается с единицы, выводить группы следует в соответствии с порядком ролей. Переводы строк между группами обязательны, переводы строк в конце текста не учитываются.
 * <p>
 * Заметим, что вам предстоит обработка огромной пьесы в 50 000 строк для 10 ролей – соответственно, неправильная сборка результирующей строчки может выйти за ограничение по времени.
 * <p>
 * Обратите внимание еще на несколько нюансов:
 * <p>
 * имя персонажа может встречаться в строке более одного раза, в том числе с двоеточием;
 * название одной роли может быть префиксом названия другой роли (например, "Лука" и "Лука Лукич");
 * роль, у которой нет реплик, тоже должна присутствовать в выходном файле;
 * в качестве перевода строки надо использовать символ '\n' (перевод строки в стиле UNIX);
 * будьте внимательны, не добавляйте лишних пробелов в конце строк.
 */
public class TextPerRole {

    /**
     * Контракт метода по заданию.
     */
    private String printTextPerRole(String[] roles, String[] textLines) {
        if (roles == null || roles.length == 0) {
            return "";
        }
        if (textLines == null || textLines.length == 0) {
            return "";
        }

        int i = 0;

        Set<String> roleSet = new HashSet<>(Arrays.asList(roles));
        Map<String, StringBuilder> textPerRoles = new LinkedHashMap<>();
        for (String line : textLines) {
            String role = "";
            int delim = line.indexOf(':');
            if (delim > 0) {
                role = line.substring(0, delim);
            }
            if (!roleSet.contains(role)) {
                continue;
            }
            if (!textPerRoles.containsKey(role)) {
                textPerRoles.put(role, new StringBuilder());
            }
            String speech = line.substring(delim + 1);
            textPerRoles.get(role).append(++i).append(")").append(speech).append("\n");
        }
        StringBuffer result = new StringBuffer();
        textPerRoles.forEach((role, text) -> {
            result.append(role).append(":\n").append(text).append("\n");
        });
        return result.toString();
    }

    public String parse(String[] roles, String[] textLines) {
        return printTextPerRole(roles, textLines);
    }
}
