package ru.zakusov.test.chapter4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RobotTest {

    private boolean connected;
    private boolean moved;
    private boolean closed;

    @Before
    public void setUp() throws Exception {
        connected = false;
        moved = false;
        closed = false;
    }

    /**
     * Попытка отдать команду роботу считается успешной, если удалось установить соединение
     * и выполнить метод RobotConnection.moveRobotTo() без исключений.
     * Ошибка закрытия соединения не важна и должна игнорироваться.
     */
    @Test
    public void testWithoutExceptions() {
        Robot.moveRobot(new RobotConnectionManager() {
            @Override
            public RobotConnection getConnection() {
                connected = true;
                return new RobotConnection() {
                    @Override
                    public void moveRobotTo(int x, int y) {
                        if (x == 1 && y == 2) {
                            moved = true;
                        }
                    }

                    @Override
                    public void close() {
                        closed = true;
                    }
                };
            }
        }, 1, 2);
        checkFlags();
    }

    /**
     * Если первая попытка подключиться и отдать команду оказалась неуспешной,
     * то закрываем соединение и выполняем вторую попытку.
     */
    @Test
    public void testReconnection() {
        Robot.moveRobot(new RobotConnectionManager() {
            int attempts = 0;

            @Override
            public RobotConnection getConnection() {
                if (attempts++ < 2) {
                    throw new RobotConnectionException("Ошибка соединения");
                }
                connected = true;
                return new RobotConnection() {
                    @Override
                    public void moveRobotTo(int x, int y) {
                        moved = true;
                    }

                    @Override
                    public void close() {
                        closed = true;
                    }
                };
            }
        }, 1, 2);
        checkFlags();
    }

    /**
     * Если первая попытка подключиться и отдать команду оказалась неуспешной,
     * то закрываем соединение и выполняем вторую попытку. Если вторая тоже не удалась, то выполняется третья.
     * После третьей неудачи метод должен бросить исключение RobotConnectionException.
     */
    @Test(expected = RobotConnectionException.class)
    public void testRobotConnectionException() {
        Robot.moveRobot(new RobotConnectionManager() {
            int attempts = 0;

            @Override
            public RobotConnection getConnection() {
                if (attempts++ < 3) {
                    System.out.println("Попытка " + attempts);
                    throw new RobotConnectionException("Ошибка соединения");
                }
                fail("Перебор");
                return null;
            }
        }, 1, 2);
        fail("Expected a RobotConnectionException");
    }

    /**
     * Если во время работы метода случилось исключение любого типа, отличного от RobotConnectionException,
     * метод должен завершиться и выбросить это исключение, не выполняя повторных попыток пообщаться с роботом.
     * Единственное, что метод должен сделать перед выбросом этого исключения — закрыть открытое соединение RobotConnection.
     */
    @Test
    public void testUnexpectedException() {
        try {
            Robot.moveRobot(new RobotConnectionManager() {

                @Override
                public RobotConnection getConnection() {
                    connected = true;
                    return new RobotConnection() {
                        @Override
                        public void moveRobotTo(int x, int y) {
                            moved = true;
                            throw new IllegalArgumentException("Проверка поведения");
                        }

                        @Override
                        public void close() {
                            closed = true;
                        }
                    };
                }
            }, 1, 2);
        } catch (IllegalArgumentException e) {
            checkFlags();
            assertTrue(closed);
            return;
        }
        fail("Expected an IllegalArgumentException");
    }

    /**
     * Ошибка закрытия соединения не важна и должна игнорироваться.
     */
    @Test
    public void testUnexpectedRobotConnectionExceptionThrown() {
        try {
            Robot.moveRobot(new RobotConnectionManager() {

                @Override
                public RobotConnection getConnection() {
                    connected = true;
                    return new RobotConnection() {
                        @Override
                        public void moveRobotTo(int x, int y) {
                            moved = true;
                        }

                        @Override
                        public void close() {
                            throw new RobotConnectionException("Исключение при закрытии соединения");
                        }
                    };
                }
            }, 1, 2);
        } catch (RobotConnectionException e) {
            fail("Failed. Test 4. Unexpected RobotConnectionException thrown");
        }
        checkFlags();
    }

    private void checkFlags() {
        assertTrue("Не выполнены условия соединения", connected);
        assertTrue("Не выполнены условия передвижения", moved);
        // Ошибка закрытия соединения не важна и должна игнорироваться.
        if (!closed) {
            System.out.println("Соединение осталось не закрыто");
        }
    }
}