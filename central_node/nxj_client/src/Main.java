import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;
import lejos.util.Delay;

public class Main {

    public static void main(String[] args) throws Exception {
        configureEscape();

        NXTConnection connection = USB.waitForConnection();
        InputStream is = connection.openInputStream();
        OutputStream os = connection.openOutputStream();

        while (true) {
            LCD.clear();
            LCD.drawString("Waiting for input...", 0, 0);

            int val = is.read();

            LCD.drawString("Value: " + val, 0, 0);

            switch (val) {
                case NXTVars.QUARTER_FORWARD: rotate90(1); break;
                case NXTVars.QUARTER_BACKWARD: rotate90(-1); break;
                case NXTVars.HALF_FORWARD: rotate45(1); break;
                case NXTVars.HALF_BACKWARD: rotate45(-1); break;
                default:
            }

            // Send ACK
            os.write(NXTVars.ACK);
            os.flush();

            LCD.drawString("Wrote ACK!", 0, 20);
        }
    }

    private static void rotate90(int mult) {
        final int deg = mult * 90;

        Motor.A.setAcceleration(3000);
        Motor.A.setSpeed(100);

        Motor.A.rotate(deg, true);
        Delay.msDelay(1000);
        Motor.A.flt();
        Delay.msDelay(500);
    }

    private static void rotate45(int mult) {
        final int deg = mult * 45;

        Motor.A.setAcceleration(1500);
        Motor.A.setSpeed(50);

        Motor.A.rotate(deg);
        Motor.A.flt();
        Delay.msDelay(2000);
    }

    private static void configureEscape() {
        Button.ESCAPE.addButtonListener(new ButtonListener() {
            @Override
            public void buttonPressed(Button b) {
                // Nothing
            }

            @Override
            public void buttonReleased(Button b) {
                System.exit(0);
            }
        });
    }
}
