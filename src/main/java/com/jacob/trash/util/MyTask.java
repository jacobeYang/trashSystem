package com.jacob.trash.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyTask {
    @Scheduled(fixedRate = Constant.TASK_DELAY_TIME)
    public void reportCurrentTime() {
        //
    }
}
