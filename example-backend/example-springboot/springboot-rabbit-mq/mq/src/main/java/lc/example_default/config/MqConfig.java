package lc.example_default.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    private final ObjectMapper jacksonObjectMapper = new ObjectMapper();

    @Bean
    public Queue orange(){
        return new Queue("orange",true);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue("jsonQueue",true);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter(jacksonObjectMapper);
    }

}
