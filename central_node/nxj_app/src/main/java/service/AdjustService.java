package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class AdjustService {

    @Autowired
    private ArmNodeMetricService metric;

    @Autowired
    private MotorService motor;

    // Executes this in a separate thread, so we can return the web request
    public void startAdjust() {
        // https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(this::doAdjust);
    }

    private static final int DELAY = 2;
    // Will ping from the client at this same frequency, 100 ms
    private static final double RTT_TIME = 0.1;

    private void doAdjust() {
        // Reset device
        device = null;
        // Reset initial movement
        forward = true;

        // Initial delay
        delay();

        // Closer to 0 is best
        // dB here will never exceed 0
        int bestSignal = Integer.MIN_VALUE;

        for (int i = 0; i < MotorService.NUM_POS - 1; i++) {
            int signal = getDeviceSignalStrength();
            if (signal > bestSignal) {
                // Got better, keep going
                // Make sure to update value
                bestSignal = signal;
            } else {
                // Got worse, flip direction
                forward = !forward;
            }
            // Includes a delay inherently
            makeMove();
        }
    }

    private boolean forward;

    private void makeMove() {
        if (forward) {
            motor.forwardTurn();
        } else {
            motor.backwardTurn();
        }
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Assume can get response in about 100 ms
    // Want to maximize this value!

    private String device;

    // Get the average of many samples
    private int getDeviceSignalStrength() {
        if (device == null) {
            Map<String, Integer> all = metric.getAllSignals();
            device = all.keySet().iterator().next();
        }

        // Take average value
        final double LIMIT = DELAY / RTT_TIME;
        double total = 0;
        for (int i = 0; i < LIMIT; i++) {
            total += metric.getSignal(device);
        }
        return (int) (total / LIMIT);
    }
}
