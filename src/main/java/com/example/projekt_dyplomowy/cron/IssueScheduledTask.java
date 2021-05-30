package com.example.projekt_dyplomowy.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class IssueScheduledTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0/10 * * * *")
    public void executeTask() {
        log.info("Task excuted at {}", dateFormat.format(new Date()));
    }
}
