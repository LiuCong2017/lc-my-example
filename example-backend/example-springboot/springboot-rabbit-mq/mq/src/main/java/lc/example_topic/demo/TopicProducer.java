package lc.example_topic.demo;

import lc.example_topic.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
    private final Logger log = LoggerFactory.getLogger(TopicProducer.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 这里我们的生产者发送的消息routingKey是sms.liantong，它就会被发到topicQueue1队列中去，这里消息的routingKey也需要用.隔离开，用其他符号无法正确识别。
     * 如果我们的routingKey是sms.123.liantong，那么它将无法找到对应的队列，因为topicQueue1的模糊匹配用的通配符是*而不是#，只有#是可以匹配多个单词的。
     */
    public void sendTopic() {
        Client client = new Client();
        log.info("Message content : " + client);
        rabbitTemplate.convertAndSend("topicExchange","sms.liantong",client);
        System.out.println("消息发送完毕。");
    }

}
