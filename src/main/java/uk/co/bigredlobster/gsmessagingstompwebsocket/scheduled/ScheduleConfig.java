package uk.co.bigredlobster.gsmessagingstompwebsocket.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduleConfig {

    @Bean
    public ScheduledTask scheduledTask() {
        return new ScheduledTask();
    }

    @Bean
    public ScheduledTask2 scheduledTask2() {
        return new ScheduledTask2();
    }

}
