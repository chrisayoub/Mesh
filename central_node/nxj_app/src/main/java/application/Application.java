package application;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("controller")
@ComponentScan("service")
public class Application {
    public static void main(String[] args) throws NXTCommException {
        // TEST CODE HERE
        NXTComm c = NXTCommFactory.createNXTComm(NXTCommFactory.USB);
        NXTInfo[] i = c.search(null);
        if (i.length == 0) {
            System.out.println("NO NXT!");
            return;
        }
        for (NXTInfo ni : i) {
            System.out.println(ni.name);
        }

        // Run here
        SpringApplication.run(Application.class, args);
    }
}
