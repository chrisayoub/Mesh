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
    // Will ping the client at this same frequency, 100 ms
    private static final double RTT_TIME = 0.1;

    /**
     * Adjustment algorithm
     *
     * Phase 1: do forward quarter turns until the signal worsens from the original
     * Phase 2: evaluate the quarter as two options: either split the difference between
     *          the two quarter marks, or go back to the previous quarter mark, whichever is better
     */
    private void doAdjust() {
        // Reset device
        device = null;

        // Initial delay
        delay();

        // Phase 1: quarter turns
        int oldSignal = getDeviceSignalStrength();
        while (true) {
            motor.forwardTurn();
            int updatedSignal = getDeviceSignalStrength();
            if (oldSignal >= updatedSignal) {
                break;
            }
            oldSignal = updatedSignal;
        }
        // Phase 2: half-quarter turns
        motor.backwardHalfTurn();
        if (oldSignal > getDeviceSignalStrength()) {
            motor.backwardHalfTurn();
        }
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String device;

    // Get the average of many samples
    // Assume can get response in about 100 ms
    // Want to maximize this value!
    // Synchronous, and has implicit delay
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
