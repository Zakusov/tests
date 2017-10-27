package ru.zakusov.test.chapter3.analyzer;

import java.util.Arrays;

/**
 * Вы хотите фильтровать комментарии по разным критериям, уметь легко добавлять новые фильтры и модифицировать старые.
 * <p>
 * Допустим, мы будем фильтровать спам, комментарии с негативным содержанием и слишком длинные комментарии.
 * Спам будем фильтровать по наличию указанных ключевых слов в тексте.
 * Негативное содержание будем определять по наличию одного из трех смайликов – :( =( :|
 * Слишком длинные комментарии будем определять исходя из данного числа – максимальной длины комментария.
 */
public class CheckLabels {

    /**
     * Контракт метода по заданию.
     */
    public Label checkLabels(TextAnalyzer[] analyzers, String text) {
        return Arrays.stream(analyzers)
                .map(analyzer -> analyzer.processText(text))
                .filter(label -> label != Label.OK)
                .findFirst().orElse(Label.OK);
    }

    abstract class KeywordAnalyzer implements TextAnalyzer {

        public Label processText(String text) {
            if (text != null) {
                for (String keyword : getKeywords()) {
                    if (text.toLowerCase().contains(keyword)) {
                        return getLabel();
                    }
                }
            }
            return Label.OK;
        }

        protected abstract String[] getKeywords();

        protected abstract Label getLabel();
    }

    class NegativeTextAnalyzer extends KeywordAnalyzer {

        protected String[] getKeywords() {
            return new String[]{":(", "=(", ":|"};
        }

        protected Label getLabel() {
            return Label.NEGATIVE_TEXT;
        }
    }

    class SpamAnalyzer extends KeywordAnalyzer {

        private String[] keywords;

        public SpamAnalyzer(String[] words) {
            keywords = words;
        }

        protected String[] getKeywords() {
            return keywords;
        }

        protected Label getLabel() {
            return Label.SPAM;
        }
    }

    class TooLongTextAnalyzer implements TextAnalyzer {

        private int maxLength;

        public TooLongTextAnalyzer(int length) {
            maxLength = length;
        }

        public Label processText(String text) {
            return text != null && text.length() > maxLength ? Label.TOO_LONG : Label.OK;
        }
    }
}
