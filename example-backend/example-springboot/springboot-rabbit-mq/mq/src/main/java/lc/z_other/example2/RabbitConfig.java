package lc.z_other.example2;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ的配置类
 * 用来配置队列、交换器、路由等高级信息
 */
@Configuration
public class RabbitConfig {

    /**
     * 保证消息可靠性-3. 消息入队之后MQ宕机
     * 这种问题就必须要对消息做持久化,队列的持久化和Exchange的持久化，以便MQ重新启动之后消息还能重新恢复过来
     * 创建Exchange和队列时只要设置好持久化，发送的消息默认就是持久化消息
     */
    @Bean
    public DirectExchange directExchange() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange("directExchange", false, false);
    }

    @Bean
    public Queue helloQueue(){
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下true持久化即可
        return new Queue("hello",true);
    }
    @Bean
    public Queue failQueue(){
        return new Queue("fail",true);
    }


}

