package ru.zakusov.test.chapter5;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Thanks to <a href="https://github.com/meanmail">meanmail</a> for the tests base.
 */
public class AnimalDeserializerTest {

    private static byte[] serialize(int count, Object[] objects) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);

        out.writeInt(count);

        for (Object object : objects) {
            out.writeObject(object);
        }

        out.flush();
        return byteStream.toByteArray();
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalZero() throws Throwable {
        byte[] data = serialize(0, new Animal[0]);
        Animal[] animals = AnimalDeserializer.deserializeAnimalArray(data);

        assertNotNull(animals);
        assertEquals(0, animals.length);
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalOne() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(1, animal);

        Animal[] animals = AnimalDeserializer.deserializeAnimalArray(data);

        assertNotNull(animals);
        assertEquals(1, animals.length);
    }

    @Test(timeout = 8000)
    public void deserializeAnimalArrayNormalThee() throws Throwable {

        Animal[] animal = new Animal[]{
                new Animal("Cat"),
                new Animal("Dog"),
                new Animal("Mouse")
        };

        byte[] data = serialize(animal.length, animal);

        Animal[] animals = AnimalDeserializer.deserializeAnimalArray(data);

        assertNotNull(animals);
        assertEquals(animal.length, animals.length);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayNotAnimal() throws Throwable {
        Object[] animal = new Object[]{
                new Integer(100)
        };

        byte[] data = serialize(animal.length, animal);

        AnimalDeserializer.deserializeAnimalArray(data);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayWrongCount() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(2, animal);

        AnimalDeserializer.deserializeAnimalArray(data);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 8000)
    public void deserializeAnimalArrayIncorrectCount() throws Throwable {
        Animal[] animal = new Animal[]{
                new Animal("Cat")
        };

        byte[] data = serialize(-10, animal);

        AnimalDeserializer.deserializeAnimalArray(data);
    }
}