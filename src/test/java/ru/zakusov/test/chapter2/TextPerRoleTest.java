package ru.zakusov.test.chapter2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextPerRoleTest {

    @Test
    public void parseVariant1() throws Exception {
        String[] roles = {"Городничий", "Аммос Федорович",
                "Артемий Филиппович", "Лука Лукич", "Семён Семёныч"};
        String[] textLines = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
        String expected = "Городничий:\n" +
                "1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.\n" +
                "4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.\n" +
                "\n" +
                "Аммос Федорович:\n" +
                "2) Как ревизор?\n" +
                "5) Вот те на!\n" +
                "\n" +
                "Артемий Филиппович:\n" +
                "3) Как ревизор?\n" +
                "6) Вот не было заботы, так подай!\n" +
                "\n" +
                "Лука Лукич:\n" +
                "7) Господи боже! еще и с секретным предписаньем!\n" +
                "\n" +
                "Семён Семёныч:\n\n";
        assertEquals(expected, new TextPerRole().parse(roles, textLines));
    }

    @Test
    public void parseVariant2() throws Exception {
        String[] roles = {
                "Gorodnichij", "Ammos Fedorovich",
                "Artemij Filippovich",
                "Luka Lukich"};
        String[] textLines = {
                "Gorodnichij: Ja priglasil vas, gospoda, s tem, chtoby soobshhit' vam preneprijatnoe izvestie: k nam edet revizor.",
                "Ammos Fedorovich: Kak revizor?",
                "Artemij Filippovich: Kak revizor?",
                "Gorodnichij: Revizor iz Peterburga, inkognito. I eshhe s sekretnym predpisan'em.",
                "Ammos Fedorovich: Vot te na!",
                "Artemij Filippovich: Vot ne bylo zaboty, tak podaj!",
                "Luka Lukich: Gospodi bozhe! eshhe i s sekretnym predpisan'em!"};
        String expected = "Gorodnichij:\n" +
                "1) Ja priglasil vas, gospoda, s tem, chtoby soobshhit' vam preneprijatnoe izvestie: k nam edet revizor.\n" +
                "4) Revizor iz Peterburga, inkognito. I eshhe s sekretnym predpisan'em.\n" +
                "\n" +
                "Ammos Fedorovich:\n" +
                "2) Kak revizor?\n" +
                "5) Vot te na!\n" +
                "\n" +
                "Artemij Filippovich:\n" +
                "3) Kak revizor?\n" +
                "6) Vot ne bylo zaboty, tak podaj!\n" +
                "\n" +
                "Luka Lukich:\n" +
                "7) Gospodi bozhe! eshhe i s sekretnym predpisan'em!\n\n";
        assertEquals(expected, new TextPerRole().parse(roles, textLines));
    }
}
