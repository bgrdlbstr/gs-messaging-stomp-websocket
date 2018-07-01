package uk.co.bigredlobster.gsmessagingstompwebsocket.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.co.bigredlobster.gsmessagingstompwebsocket.Greeting;

import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledTask2 {

    @Autowired
    private SimpMessagingTemplate template;

    private ObjectMapper mapper = new ObjectMapper();
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Scheduled(fixedDelay = 2000)
    public void scheduleUpdate() {
        final int i = atomicInteger.incrementAndGet();
        System.out.println("Sending goodbye - " + i + "...");
        Greeting greeting = new Greeting("goodbye! - " + i);
        try {
            template.convertAndSend("/topic/greetings", mapper.writeValueAsString(greeting));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
