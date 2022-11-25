package lc.example_direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqDirectExchangeConfig {
    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directQueue2");
    }

    @Bean
    public DirectExchange directExchange(){
        // 三个构造参数：name durable autoDelete
        return new DirectExchange("directExchange",false,false);
    }

    /**
     * 交换机和队列建立绑定关系的时候设置的routingKey，
     * 一个消息到达交换机之后，
     * 交换机通过消息上带来的routingKey找到自己与队列建立绑定关系时设置的routingKey，
     * 然后将消息分发到这个队列去
     */
    @Bean
    public Binding directBinding1(){
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("mail");
    }

}
