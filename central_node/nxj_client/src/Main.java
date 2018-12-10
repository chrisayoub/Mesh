import java.io.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

public class Main {

    private static final int QUARTER = 90;
    private static final int HALF = QUARTER / 2;

    public static void main(String[] args) {
        configureEscape();

        NXTConnection connection = USB.waitForConnection();
        InputStream is = connection.openInputStream();
        OutputStream os = connection.openOutputStream();

        try {
            while (true) {
                int val = is.read();

                switch (val) {
                    case NXTVars.QUARTER_FORWARD: rotate(QUARTER); break;
                    case NXTVars.QUARTER_BACKWARD: rotate(-QUARTER); break;
                    case NXTVars.HALF_FORWARD: rotate(HALF); break;
                    case NXTVars.HALF_BACKWARD: rotate(-HALF); break;
                    default:
                }

                // Send ACK
                os.write(NXTVars.ACK);
                os.flush();
            }
        } catch (Exception e) {
            LCD.drawString(e.getMessage(), 0, 0);
        }
    }

    private static void rotate(int deg) {
        Motor.A.rotate(deg);
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
