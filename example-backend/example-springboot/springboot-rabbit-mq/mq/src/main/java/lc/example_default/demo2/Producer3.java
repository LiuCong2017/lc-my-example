package lc.example_default.demo2;

import lc.example_default.entity.TestEntityNoSerialzable;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer3 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendObject(){
        rabbitTemplate.convertAndSend("jsonQueue",new TestEntityNoSerialzable("json message"));
    }

}
