package ru.zakusov.test.chapter6;

import java.util.*;
import java.util.function.Consumer;

/**
 * Вам нужно реализовать классы MailService, MailMessage и Salary (и, вероятно, вспомогательные классы и интерфейсы)
 * и отправить их код в форму. Все классы должны быть публичными и статическими (ваши классы подставятся
 * во внешний класс для тестирования).
 * <p>
 * В идеологически правильном решении не должно фигурировать ни одного оператора instanceof.
 */
// TODO Your code complexity score is 21.91 (less is better).
public class PostOffice {

    public static void main(String[] args) {

        String randomFrom = "..."; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        String randomTo = "...";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
        int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

        // Создание списка из трех почтовых сообщений.
        MailMessage firstMessage = new MailMessage(
                "Robert Howard",
                "H.P. Lovecraft",
                "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
        );

        assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
        assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
        assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

        MailMessage secondMessage = new MailMessage(
                "Jonathan Nolan",
                "Christopher Nolan",
                "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
        );

        MailMessage thirdMessage = new MailMessage(
                "Stephen Hawking",
                "Christopher Nolan",
                "Я так и не понял Интерстеллар."
        );

        List<MailMessage> messages = Arrays.asList(
                firstMessage, secondMessage, thirdMessage
        );

        // Создание почтового сервиса.
        MailService<String> mailService = new MailService<>();

        // Обработка списка писем почтовым сервисом
        messages.stream().forEachOrdered(mailService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список сообщений, которые были ему отправлены
        Map<String, List<String>> mailBox = mailService.getMailBox();

        System.out.println(mailBox.get("H.P. Lovecraft"));
        assert mailBox.get("H.P. Lovecraft").equals(
                Arrays.asList(
                        "This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
                )
        ) : "wrong mailService mailbox content (1)";

        assert mailBox.get("Christopher Nolan").equals(
                Arrays.asList(
                        "Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
                        "Я так и не понял Интерстеллар."
                )
        ) : "wrong mailService mailbox content (2)";

        assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";


        // Создание списка из трех зарплат.
        Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
        Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
        Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

        // Создание почтового сервиса, обрабатывающего зарплаты.
        MailService<Integer> salaryService = new MailService<>();

        // Обработка списка зарплат почтовым сервисом
        Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

        // Получение и проверка словаря "почтового ящика",
        //   где по получателю можно получить список зарплат, которые были ему отправлены.
        Map<String, List<Integer>> salaries = salaryService.getMailBox();
        assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
        assert salaries.get(salary2.getTo()).equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
        assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";
    }

    // Решение задачи

    private interface Message<T> {
        String getTo();

        T getContent();
    }

    private static class AbstractMessage<T> implements Message<T> {
        String from;
        String to;
        T content;

        public AbstractMessage(String sender, String recipient, T message) {
            from = sender;
            to = recipient;
            content = message;
        }

        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public T getContent() {
            return content;
        }
    }

    public static class MailMessage extends AbstractMessage<String> {
        public MailMessage(String sender, String recipient, String message) {
            super(sender, recipient, message);
        }
    }

    public static class Salary extends AbstractMessage<Integer> {
        public Salary(String sender, String recipient, Integer message) {
            super(sender, recipient, message);
        }
    }

    public static class MailService<T> implements Consumer<Message<T>> {

        private Map<String, List<T>> mailBox = new HashMap<String, List<T>>() {
            @Override
            public List<T> get(Object key) {
                return containsKey(key) ? super.get(key) : Collections.emptyList();
            }
        };

        @Override
        public void accept(Message<T> message) {
            System.out.println("before: " + mailBox);
            mailBox.compute(message.getTo(), (recipient, messages) -> {
                if (messages == null) {
                    messages = new LinkedList<>();
                }
                messages.add(message.getContent());
                return messages;
            });
            System.out.println("after : " + mailBox);
        }

        /**
         * @return получателей и их письма.
         */
        public Map<String, List<T>> getMailBox() {
            return mailBox;
        }
    }
}
