package lc.example1.demo1;

import com.rabbitmq.client.Channel;
import lc.example1.entity.TestEntiry;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class Consumer2 {

    @RabbitHandler
    public void onMessage(@Payload String strMsg, Message message, Channel channel) throws IOException {
        System.out.println(strMsg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        System.out.println("消息已确认");
    }

    @RabbitHandler
    public void onMessage(@Payload TestEntiry te, Message message, Channel channel) throws IOException {
        System.out.println(te);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
