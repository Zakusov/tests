package ru.zakusov.test.chapter4;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.zakusov.test.chapter4.PostService.*;

public class PostServiceTest {

    private Logger logger;
    private StringHandler handler;

    private MailMessage mailToOnegin;
    private MailMessage mailToAustin;
    private MailPackage weaponPackage;
    private MailPackage cheapPackage;
    private MailPackage valuablePackage;
    private MailPackage stolenPackage;

    public PostServiceTest() {
        handler = new StringHandler();
        logger = Logger.getLogger(PostServiceTest.class.getName());
        logger.addHandler(handler);
    }

    @Before
    public void setUp() throws Exception {
        mailToOnegin = new MailMessage("Tatyana", "Onegin", "I write this to you - what more can be said?");
        mailToAustin = new MailMessage("Tatyana", "Austin Powers", "I write this to you - what more can be said?");

        weaponPackage = new MailPackage("Mumiy", "Troll", new PostService.Package("weapons", 10000));
        cheapPackage = new MailPackage("Mumiy", "Troll", new PostService.Package("banned substance", 100));
        valuablePackage = new MailPackage("Mumiy", "Vladivostok", new PostService.Package("Love", 2000));
        stolenPackage = new MailPackage("Mumiy", "Vladivostok", new PostService.Package("stones instead of Love", 0));
    }

    /**
     * 1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
     * чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц,
     * а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService.
     * У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail
     * первого элемента массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService,
     * который возвращает ссылку на внутренний экземпляр RealMailService.
     */
    @Test
    public void testUntrustworthyMailWorker() {
        Spy spy = new Spy(logger);
        Thief thief = new Thief(100);
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(new MailService[]{spy, thief});
        assertTrue(worker.getRealMailService() instanceof RealMailService);
        assertEquals(mailToOnegin, worker.processMail(mailToOnegin));
    }

    /**
     * 2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки.
     * Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.
     * Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения
     * (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
     * 2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение
     * с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
     * 2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}
     */
    @Test
    public void testSpy() {
        Spy spy = new Spy(logger);
        assertEquals(mailToOnegin, spy.processMail(mailToOnegin));
        assertEquals("Usual correspondence: from {0} to {1}", handler.getLastMessage());
        assertEquals(mailToAustin, spy.processMail(mailToAustin));
        assertEquals("Detected target mail correspondence: from {0} to {1} \"{2}\"", handler.getLastMessage());
    }

    /**
     * 3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе
     * переменную int – минимальную стоимость посылки, которую он будет воровать.
     * Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок,
     * которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору,
     * он отдает новую, такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".
     */
    @Test
    public void testThief() {
        Thief thief = new Thief(1000);

        assertEquals(mailToOnegin, thief.processMail(mailToOnegin));
        assertEquals(0, thief.getStolenValue());

        assertEquals(cheapPackage, thief.processMail(cheapPackage));
        assertEquals(0, thief.getStolenValue());

        assertEquals(stolenPackage, thief.processMail(valuablePackage));
        assertEquals(2000, thief.getStolenValue());

        assertEquals(stolenPackage, thief.processMail(valuablePackage));
        assertEquals(4000, thief.getStolenValue());
    }

    /**
     * 4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
     * если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым
     * ("weapons" и "banned substance"), то он бросает IllegalPackageException.
     * Если он находит посылку, состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде
     * StolenPackageException. Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
     */
    @Test
    public void testInspector() {
        Inspector inspector = new Inspector();
        inspector.processMail(mailToOnegin);
    }

    @Test(expected = IllegalPackageException.class)
    public void testInspectorForWeaponsCase() {
        Inspector inspector = new Inspector();
        inspector.processMail(weaponPackage);
    }

    @Test(expected = IllegalPackageException.class)
    public void testInspectorForBannedSubstanceCase() {
        Inspector inspector = new Inspector();
        inspector.processMail(cheapPackage);
    }

    @Test(expected = StolenPackageException.class)
    public void testInspectorForStonesCase() {
        Inspector inspector = new Inspector();
        inspector.processMail(stolenPackage);
    }

    private static class StringHandler extends Handler {

        private String lastMessage;

        @Override
        public void publish(LogRecord record) {
            lastMessage = record.getMessage();
        }

        public String getLastMessage() {
            return lastMessage;
        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }
    }
}