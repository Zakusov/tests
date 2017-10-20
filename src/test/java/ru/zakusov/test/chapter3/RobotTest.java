package ru.zakusov.test.chapter3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobotTest {

    @Test
    public void moveOnce() throws Exception {
        Robot robot = buildRobot(0, 0, Robot.Direction.UP);
        testMoving(robot, 3, 0);
    }

    @Test
    public void moveDance() throws Exception {
        Robot robot = buildRobot(0, 0, Robot.Direction.UP);
        testMoving(robot, -3, 4);
        testMoving(robot, 2, -2);
        testMoving(robot, 5, 5);
        testMoving(robot, 1, 0);
        testMoving(robot, 2, 0);
        testMoving(robot, 0, 0);
        testMoving(robot, 0, 0);
    }

    private Robot buildRobot(int x, int y, Robot.Direction direction) {
        Robot robot = new Robot(x, y, direction);
        assertEquals(x, robot.getX());
        assertEquals(y, robot.getY());
        assertEquals(direction, robot.getDirection());
        return robot;
    }

    private void testMoving(Robot robot, int toX, int toY) {
        robot.move(toX, toY);
        assertEquals(toX, robot.getX());
        assertEquals(toY, robot.getY());
    }
}