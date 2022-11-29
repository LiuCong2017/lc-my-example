package lc.z_other.example2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
@RabbitListener(queues="test") //通过@RabbitListener注解定义该类对test队列的监听
public class Receiver {

    /**
     * 保证消息可靠性-4. 消费者无法正常消费
     *  # 手动确认消息
     *     listener:
     *       simple:
     *           acknowledge-mode: manual
     * 打开手动消息确认之后，只要这条消息没有成功消费，无论中间是出现消费者宕机还是代码异常，只要连接断开之后这条信息还没有被消费那么这条消息就会被重新放入队列再次被消费。
     * 后续改进:
     * 1)消息重复消费怎么办？
     * 2)某些任务需要消息的顺序消息，顺序消费怎么保证？
     */


    //指定对消息的处理方法
    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver : " + hello);
    }

}
