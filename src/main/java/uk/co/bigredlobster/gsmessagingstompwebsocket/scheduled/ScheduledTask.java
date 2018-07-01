package uk.co.bigredlobster.gsmessagingstompwebsocket.scheduled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import uk.co.bigredlobster.gsmessagingstompwebsocket.Greeting;

import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledTask {

    @Autowired
    private SimpMessagingTemplate template;

    private ObjectMapper mapper = new ObjectMapper();
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000)
    public void scheduleUpdate() {
        final int i = atomicInteger.incrementAndGet();
        System.out.println("Sending hello - " + i + "...");
        Greeting greeting = new Greeting("hello! " + i);
        try {
            template.convertAndSend("/topic/greetings", mapper.writeValueAsString(greeting));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
