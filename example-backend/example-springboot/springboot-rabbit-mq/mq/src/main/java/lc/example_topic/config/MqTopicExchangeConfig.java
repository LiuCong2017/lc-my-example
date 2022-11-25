package lc.example_topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic-Exchange是直接交换机的模糊匹配版本，Topic类型的交换器，支持使用"*"和"#"通配符定义模糊bindingKey，然后按照routingKey进行模糊匹配队列进行分发。
 * *：能够模糊匹配一个单词。
 * #：能够模糊匹配零个或多个单词。
 * 因为加入了两个通配定义符，所以Topic交换机的routingKey也有些变化，routingKey可以使用.将单词分开。
 */
@Configuration
public class MqTopicExchangeConfig {

    // 主题交换机示例
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2");
    }

    @Bean
    public TopicExchange topicExchange() {
        // 三个构造参数：name durable autoDelete
        return new TopicExchange("topicExchange", false, false);
    }

    /**
     * 这里的示例代码我们主要看设置routingKey，这里的routingKey用上了通配符，且中间用.隔开，这就代表topicQueue1消费sms开头的消息，topicQueue2消费mail开头的消息
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("sms.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("mail.#");
    }

}
