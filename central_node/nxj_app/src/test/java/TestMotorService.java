import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import service.MotorService;

//@RunWith(JUnit4.class)
public class TestMotorService {

    private MotorService m = new MotorService();

//    @Test
    public void testRotForward() {
        m.forwardTurn();
        m.backwardHalfTurn();
    }

//    @Test
    public void testRotBackHalf() {
        m.backwardHalfTurn();
    }
}
