import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.ArmNodeMetricService;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//@RunWith(JUnit4.class)
public class TestArmNodeMetricService {

    private ArmNodeMetricService svc = new ArmNodeMetricService();

//    @Test
    public void testGetMetrics() {
        Map<String, Integer> result = svc.getAllSignals();
        assertFalse(result.isEmpty());
        System.out.println(result);
    }

//    @Test
    public void testStartPing() {
        assertTrue(svc.startClientPing());
    }

//    @Test
    public void testStopPing() {
        assertTrue(svc.stopClientPing());
    }

//    @Test
    public void alwaysPass() {
    }
}
