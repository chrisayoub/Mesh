package me.cayoub;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.MotorPort;

/**
 * Example usage:
 *
 *     MotorController mc = new MotorController();
 *     for (int i = 0; i < 10; i++) {
 *         mc.forwardTurn();
 *         Thread.sleep(2000);
 *         mc.backwardTurn();
 *         Thread.sleep(2000);
 *     }
 */
public class MotorController {

    private MotorPort m = MotorPort.A;

    public static void main(String[] args) throws Exception {
        MotorController mc = new MotorController();

        for (int i = 0; i < 10; i++) {
            mc.forwardTurn();
            Thread.sleep(2000);
            mc.backwardTurn();
            Thread.sleep(2000);
        }
    }

    public void forwardTurn() {
        makeTurn(BasicMotorPort.FORWARD);
    }

    public void backwardTurn() {
        makeTurn(BasicMotorPort.BACKWARD);
    }

    private static final int POWER = 5;
    private static final int DELAY = 2000;

    private void makeTurn(int dir) {
        m.controlMotor(POWER, dir);
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m.controlMotor(0, BasicMotorPort.STOP);
    }
}
