package com.minte9.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronoController {

    private Boolean start = true;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private String lastTime = dateFormat.format(new Date());

    @GetMapping("/getcurrtime")
    public Crono crono() {
        if (start) {
            String time = dateFormat.format(new Date());
            lastTime = time;
        }        
        return new Crono(lastTime);
    }

    @GetMapping("/start")
    public void start() { 
        this.start = true; 
    }

    @GetMapping("/stop")
    public void stop() { 
        this.start = false; 
    }
}

/**
 * Data representation class (to return as JSON)
 */
class Crono {
    private final String time;

    public Crono(String time) { 
        this.time = time; 
    }

    public String getTime() { 
        return time; 
    }
}
