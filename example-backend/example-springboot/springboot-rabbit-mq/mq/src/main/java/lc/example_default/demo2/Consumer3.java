package lc.example1.demo2;

import com.rabbitmq.client.Channel;
import lc.example1.entity.TestEntityNoSerialzable;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "jsonQueue")
public class Consumer3 {
    @RabbitHandler
    public void onMessage(TestEntityNoSerialzable client, @Headers Map<String,Object> headers, Channel channel) throws Exception {
        System.out.println(">>>>>>>>>");
        System.out.println("Message content : " + client);
        System.out.println("Message headers : " + headers);
        channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG),false);
        System.out.println("消息已确认");
    }
}
