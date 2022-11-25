package lc.example_direct.demo;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {
    private final Logger log = LoggerFactory.getLogger(DirectConsumer.class);

    @RabbitListener(queues = "directQueue1")
    public void onMessage1(Message message, Channel channel) throws Exception {
        log.info("directQueue1 Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

    @RabbitListener(queues = "directQueue2")
    public void onMessage2(Message message, Channel channel) throws Exception {
        log.info("directQueue2 Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

}
