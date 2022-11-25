package lc.example1.demo1;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("rabbitConsumer")
@RabbitListener(queues = {"orange","hello"})
public class Consumer1 {

    @RabbitListener(queues = "orange")
    public void onMessage(Message msg, Channel channel) throws IOException {
        System.out.println(msg);
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = {"orange","hello"})
    public void onMessage(@Payload String body, @Headers Map<String,Object> headers) throws Exception {
        System.out.println("Message content : " + body);
        System.out.println("Message headers : " + headers);
    }

}
