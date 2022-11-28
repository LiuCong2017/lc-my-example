package lc.example_fanout.demo;

import lc.example_fanout.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutProducer {
    private final Logger log = LoggerFactory.getLogger(FanoutProducer.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendFanout(){
        Client client = new Client();
        log.info("Message: "+client);

        rabbitTemplate.convertAndSend("fanoutExchange",null,client);
        System.out.println("消息发送完毕");
    }



}
