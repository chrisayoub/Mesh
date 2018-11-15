package service;

import lejos.nxt.BasicMotorPort;
import lejos.nxt.MotorPort;
import org.springframework.stereotype.Service;

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
@Service
public class MotorService {

    public static final int NUM_POS = 4;

    private MotorPort m;

    public MotorService() {
        try {
            m = MotorPort.A;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot access NXT!");
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
