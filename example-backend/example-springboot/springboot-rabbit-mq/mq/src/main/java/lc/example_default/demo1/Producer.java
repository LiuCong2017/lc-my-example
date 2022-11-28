package lc.example_default.demo1;

import lc.example_default.entity.TestEntiry;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class Producer {

    private static final String QUEUE_NAME = "hello";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String msg = "test msg"+ LocalDateTime.now();
        MessageProperties props = MessagePropertiesBuilder.newInstance().setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build();
        rabbitTemplate.send(Producer.QUEUE_NAME,new Message(msg.getBytes(StandardCharsets.UTF_8),props));
        System.out.println("Send message");
    }

    public void convertAndSend(){
        TestEntiry te = new TestEntiry();
        rabbitTemplate.convertAndSend(Producer.QUEUE_NAME,te);
        System.out.println("Send message");
    }

}
