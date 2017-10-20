package ru.zakusov.test.chapter3;

/**
 * На игровом поле находится робот. Позиция робота на поле описывается двумя целочисленным координатами: X и Y.
 * Ось X смотрит слева направо, ось Y — снизу вверх. (Помните, как рисовали графики функций в школе?)
 * <p>
 * В начальный момент робот находится в некоторой позиции на поле.
 * Также известно, куда робот смотрит: вверх, вниз, направо или налево.
 * Ваша задача — привести робота в заданную точку игрового поля.
 */
public class Robot {

    private int x;
    private int y;
    private Direction direction;

    public Robot(int initialX, int initialY, Direction initialDirection) {
        x = initialX;
        y = initialY;
        direction = initialDirection;
    }

    /**
     * Контракт метода по заданию.
     */
    public static void moveRobot(Robot robot, int toX, int toY) {
        // Решение в лоб: сначала двигаем по горизонтали, потом по вертикали.
        while (robot.getX() != toX) {
            if (robot.getX() > toX) {
                // Идём налево
                while (robot.getDirection() != Direction.LEFT) {
                    robot.turnRight();
                }
            } else {
                // Идём направо
                while (robot.getDirection() != Direction.RIGHT) {
                    robot.turnRight();
                }
            }
            robot.stepForward();
        }
        while (robot.getY() != toY) {
            if (robot.getY() > toY) {
                // Идём вниз
                while (robot.getDirection() != Direction.DOWN) {
                    robot.turnRight();
                }
            } else {
                // Идём вверх
                while (robot.getDirection() != Direction.UP) {
                    robot.turnRight();
                }
            }
            robot.stepForward();
        }
    }

    /**
     * @return текущее направление взгляда
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @return текущая координата X
     */
    public int getX() {
        return x;
    }

    /**
     * @return текущая координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Повернуться на 90 градусов против часовой стрелки.
     */
    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = Direction.LEFT;
                break;
            case LEFT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.RIGHT;
                break;
            default:
                direction = Direction.UP;
        }
    }

    /**
     * Повернуться на 90 градусов по часовой стрелке.
     */
    public void turnRight() {
        switch (direction) {
            case UP:
                direction = Direction.RIGHT;
                break;
            case RIGHT:
                direction = Direction.DOWN;
                break;
            case DOWN:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.UP;
        }
    }

    /**
     * Шаг в направлении взгляда.
     * За один шаг робот изменяет одну свою координату на единицу.
     */
    public void stepForward() {
        switch (direction) {
            case UP:
                y++;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y--;
                break;
            default:
                x--;
        }
    }

    /**
     * Двигаемся в заданную точку.
     *
     * @param toX координата X.
     * @param toY координата Y.
     */
    public void move(int toX, int toY) {
        moveRobot(this, toX, toY);
    }

    /**
     * Направление взгляда робота.
     */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
