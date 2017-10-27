package ru.zakusov.test.chapter3.analyzer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckLabelsTest {

    private CheckLabels.NegativeTextAnalyzer negativeTextAnalyzer;
    private CheckLabels.SpamAnalyzer spamAnalyzer;
    private CheckLabels.TooLongTextAnalyzer lengthAnalyzer;

    @Before
    public void setUp() throws Exception {
        CheckLabels checker = new CheckLabels();
        negativeTextAnalyzer = checker.new NegativeTextAnalyzer();
        spamAnalyzer = checker.new SpamAnalyzer(new String[]{"bad"});
        lengthAnalyzer = checker.new TooLongTextAnalyzer(13);
    }

    @Test
    public void checkLabelsNegative() throws Exception {
        TextAnalyzer[] analyzers = {negativeTextAnalyzer};
        assertEquals(Label.OK, check(analyzers, "Good comment"));
        assertEquals(Label.NEGATIVE_TEXT, check(analyzers, "Bad comment :("));
    }

    @Test
    public void checkLabelsSpam() throws Exception {
        TextAnalyzer[] analyzers = {spamAnalyzer};
        assertEquals(Label.OK, check(analyzers, "Good comment"));
        assertEquals(Label.SPAM, check(analyzers, "Bad comment :("));
    }

    @Test
    public void checkLabelsLength() throws Exception {
        TextAnalyzer[] analyzers = {lengthAnalyzer};
        assertEquals(Label.OK, check(analyzers, "Good comment"));
        assertEquals(Label.TOO_LONG, check(analyzers, "Too long comment"));
    }

    @Test
    public void checkLabels() throws Exception {
        TextAnalyzer[] analyzers = {negativeTextAnalyzer, spamAnalyzer};
        assertEquals(Label.OK, check(analyzers, "Good comment"));
        assertEquals(Label.NEGATIVE_TEXT, check(analyzers, "Bad comment :("));
    }

    @Test
    public void checkLabelsForNull() throws Exception {
        TextAnalyzer[] analyzers = {negativeTextAnalyzer, spamAnalyzer, lengthAnalyzer};
        assertEquals(Label.OK, check(analyzers, null));
    }

    private Label check(TextAnalyzer[] analyzers, String text) {
        return new CheckLabels().checkLabels(analyzers, text);
    }
}