package service;

import lejos.pc.comm.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static common.NXTVars.*;

@Service
public class MotorService {

    private InputStream is;
    private OutputStream os;

    public MotorService() {
        try {
            NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
            NXTInfo nxt = nxtComm.search(null)[0];
            nxtComm.open(nxt);
            is = nxtComm.getInputStream();
            os = nxtComm.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot access NXT!");
        }
    }

    public void forwardTurn() {
        sendMsg(QUARTER_FORWARD);
    }

    public void forwardHalfTurn() {
        sendMsg(HALF_FORWARD);
    }

    public void backwardTurn() {
        sendMsg(QUARTER_BACKWARD);
    }

    public void backwardHalfTurn() {
        sendMsg(HALF_BACKWARD);
    }

    private void sendMsg(int msg) {
        try {
            os.write(msg);
            os.flush();
            is.read(); // ACK
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
