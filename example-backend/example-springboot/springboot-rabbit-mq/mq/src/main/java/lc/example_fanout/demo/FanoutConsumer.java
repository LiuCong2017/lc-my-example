package lc.example_fanout.demo;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FanoutConsumer {
    private final Logger log = LoggerFactory.getLogger(FanoutProducer.class);

    @RabbitListener(queues = "fanout1")
    public void onMessage1(Message message, Channel channel) throws IOException {
        log.info("fanout1 Message content: "+message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

    @RabbitListener(queues = "fanout2")
    public void onMessage2(Message message, Channel channel) throws Exception {
        log.info("fanout2 Message content : " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("消息已确认");
    }

}
