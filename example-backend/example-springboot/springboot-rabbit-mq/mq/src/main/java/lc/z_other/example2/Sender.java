package lc.z_other.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 消息生产者
 */
@Component
public class Sender {
    private final Logger log = LoggerFactory.getLogger(Sender.class);

//    @Autowired
//    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        //用来做消息的唯一标识
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        StringBuilder context = new StringBuilder( "hello " + new Date());
        System.out.println("Sender : " + context);
        //产生一个字符串，并发送到名为hello的队列中
        this.rabbitTemplate.convertAndSend("hello", context,correlationData);

        /**
         * 保证消息可靠性-1. 生产者发送消息到MQ (成功/失败)
         */
        this.rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("Ack status : " + ack);
                log.info("Cause content : " + cause);
                if(ack){
                    log.info("消息成功发送");
                }else{
                    log.info("消息发送失败："+correlationData+", 出现异常："+cause);
                }
            }
        });


        /**
         * 保证消息可靠性-2. MQ收到消息保证分发到了消息对应的Exchange (MQ接收失败或者路由失败,拿到被退回消息的所有信息，然后再进行处理，比如放到一个新的队列单独处理，路由失败一般都是配置问题了)
         *
         * MQ可能出现两个问题：
         * 1).消息找不到对应的Exchange。
         * 2).找到了Exchange但是找不到对应的Queue。
         */
        this.rabbitTemplate.setReturnsCallback((message) -> {
            log.info("被退回的消息为：{}", message);
        });
        //MQ接收失败返回到客户端，则放到"fail"队列单独处理
        this.rabbitTemplate.convertAndSend("fail",context);
        log.info("消息发送完毕。");
    }

}
