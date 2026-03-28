package com.minte9.scheduled_task;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component // Look Here
public class Task {
    
    private static final SimpleDateFormat dateFormat = 
        new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000) // 1 second
    public void runningTime() {
        System.out.println(
            "Time is: " + dateFormat.format(new Date())
        );
    }
}
