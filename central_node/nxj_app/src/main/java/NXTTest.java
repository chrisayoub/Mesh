import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class NXTTest {
    public static void main(String[] args) throws NXTCommException {
        NXTComm c = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
        NXTInfo[] i = c.search(null);
        if (i.length == 0) {
            System.out.println("NO NXT!");
            return;
        }
        for (NXTInfo ni : i) {
            System.out.println(ni.name);
        }
    }
}