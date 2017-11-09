package ru.zakusov.test.chapter5;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * Реализуйте метод, который из переданного массива байт восстановит массив объектов Animal.
 * Массив байт устроен следующим образом. Сначала идет число типа int, записанное при помощи
 * ObjectOutputStream.writeInt(size). Далее подряд записано указанное количество объектов типа Animal,
 * сериализованных при помощи ObjectOutputStream.writeObject(animal).
 * <p>
 * Если вдруг массив байт не является корректным представлением массива экземпляров Animal,
 * то метод должен бросить исключение java.lang.IllegalArgumentException.
 * <p>
 * Причины некорректности могут быть разные. Попробуйте подать на вход методу разные некорректные
 * данные и посмотрите, какие исключения будут возникать. Вот их-то и нужно превратить
 * в IllegalArgumentException и выбросить. Если что-то забудете, то проверяющая система подскажет.
 * Главное не глотать никаких исключений, т.е. не оставлять нигде пустой catch.
 */
public class AnimalDeserializer {

    /**
     * Контракт метода по заданию.
     */
    public static Animal[] deserializeAnimalArray(byte[] data) {
        try (ObjectInputStream stream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int count = stream.readInt();
            Animal[] animals = new Animal[count];
            for (int i = 0; i < count; i++) {
                animals[i] = (Animal) stream.readObject();
            }
            return animals;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
