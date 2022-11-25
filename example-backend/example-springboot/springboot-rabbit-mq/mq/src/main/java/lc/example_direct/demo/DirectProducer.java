package lc.example_direct.demo;

import lc.example_direct.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectProducer {
    private final Logger log = LoggerFactory.getLogger(DirectProducer.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendDirect() {
        Client client = new Client();
        log.info("Message content : " + client);
        rabbitTemplate.convertAndSend("directExchange","sms",client);
        System.out.println("消息发送完毕。");
    }

}
