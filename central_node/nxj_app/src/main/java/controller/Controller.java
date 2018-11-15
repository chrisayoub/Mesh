package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AdjustService;

@RestController
public class Controller {

    @Autowired
    private AdjustService service;

    @RequestMapping("/")
    public ResponseEntity startAdjust() {
        // Create new thread to do the adjusting after a delay
        // Then return, so that the client can start pinging the arm node
        service.startAdjust();
        return ResponseEntity.ok().build();
    }
}
