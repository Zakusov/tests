package ru.zakusov.test.chapter4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Это задачка совмещает тренировку по материалу предыдущих двух модулей – необходимо разобраться
 * и написать объект-ориентированный код и при этом коснуться свежих тем – исключений и логирования.
 * <p>
 * Дан набор классов, описывающих работу гипотетической почтовой системы.
 */
public class PostService {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    /**
     * Интерфейс: сущность, которую можно отправить по почте.
     * У такой сущности можно получить от кого и кому направляется письмо.
     */
    public interface Sendable {
        String getFrom();

        String getTo();
    }

    /**
     * Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
     */
    public interface MailService {
        Sendable processMail(Sendable mail);
    }

    /**
     * Абстрактный класс,который позволяет абстрагировать логику хранения
     * источника и получателя письма в соответствующих полях класса.
     */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            return to.equals(that.to);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " from " + from + " to " + to;
        }
    }

    /**
     * Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
     */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            return message != null ? message.equals(that.message) : that.message == null;
        }

        @Override
        public String toString() {
            return super.toString() + " \"" + message + "\"";
        }
    }

    /**
     * Посылка, содержимое которой можно получить с помощью метода `getContent`
     */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            return content.equals(that.content);
        }

        @Override
        public String toString() {
            return super.toString() + " " + content;
        }
    }

    /**
     * Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
     */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            return content.equals(aPackage.content);
        }

        @Override
        public String toString() {
            return "\"" + content + "\", price = " + price;
        }
    }

    /**
     * Класс, в котором скрыта логика настоящей почты
     */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

/*
Вам необходимо описать набор классов, каждый из которых является MailService:
*/

    /**
     * 1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
     * чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц,
     * а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService.
     * У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail
     * первого элемента массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService,
     * который возвращает ссылку на внутренний экземпляр RealMailService.
     */
    public static class UntrustworthyMailWorker implements MailService {

        private MailService[] services;
        private RealMailService realService;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            services = mailServices;
            realService = new RealMailService();
        }

        public RealMailService getRealMailService() {
            return realService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable serviced = mail;
            for (MailService service : services) {
                serviced = service.processMail(serviced);
            }
            return realService.processMail(serviced);
        }
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
    public static class Spy implements MailService {

        private Logger logger;

        public Spy(Logger spyLogger) {
            logger = spyLogger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (AUSTIN_POWERS.equals(mail.getFrom()) || AUSTIN_POWERS.equals(mail.getTo())) {
                    String message = ((MailMessage) mail).getMessage();
                    String format = "Detected target mail correspondence: from {0} to {1} \"{2}\"";
                    logger.log(Level.WARNING, format, new Object[]{mail.getFrom(), mail.getTo(), message});
                } else {
                    String format = "Usual correspondence: from {0} to {1}";
                    logger.log(Level.INFO, format, new Object[]{mail.getFrom(), mail.getTo()});
                }
            }
            return mail;
        }
    }

    /**
     * 3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе
     * переменную int – минимальную стоимость посылки, которую он будет воровать.
     * Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок,
     * которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору,
     * он отдает новую, такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".
     */
    public static class Thief implements MailService {

        private int minPrice;
        private int stolenValue;

        public Thief(int minimum) {
            minPrice = minimum;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                int price = mailPackage.getContent().getPrice();
                if (price >= minPrice) {
                    stolenValue += price;
                    return createMailPackageCopy(mailPackage);
                }
            }
            return mail;
        }

        private Sendable createMailPackageCopy(MailPackage mailPackage) {
            Package content = mailPackage.getContent();
            String newContent = String.format("stones instead of %s", content.getContent());
            Package newPackage = new Package(newContent, 0);
            return new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), newPackage);
        }
    }

    /**
     * 4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
     * если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым
     * ("weapons" и "banned substance"), то он бросает IllegalPackageException.
     * Если он находит посылку, состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде
     * StolenPackageException. Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
     */
    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                String content = ((MailPackage) mail).getContent().getContent();
                if (content != null) {
                    if (content.contains(WEAPONS) || content.contains(BANNED_SUBSTANCE)) {
                        throw new IllegalPackageException();
                    }
                    if (content.contains("stones")) {
                        throw new StolenPackageException();
                    }
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {

    }

    public static class StolenPackageException extends RuntimeException {

    }

 /*
Все классы должны быть определены как публичные и статические, так как в процессе проверки ваш код будет подставлен
во внешний класс, который занимается тестированием и проверкой структуры. Для удобства во внешнем классе объявлено
несколько удобных констант и импортировано все содержимое пакета java.util.logging. Для определения, посылкой
или письмом является Sendable объект воспользуйтесь оператором instanceof.
 */
}
