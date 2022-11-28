package lc;

import lc.example_default.demo1.Producer;
import lc.example_default.demo2.Producer3;
import lc.example_fanout.demo.FanoutProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {
    @Autowired
    private Producer producer;
    @Autowired
    private Producer3 producer3;
    @Autowired
    private FanoutProducer fanoutProducer;

    @Test
    public void sendMsg(){
        producer.send();
        producer.convertAndSend();
    }

    @Test
    public void sendJsonMsg(){
        producer3.sendObject();
    }

    @Test
    public void sendFanoutMessage(){
        fanoutProducer.sendFanout();
    }

}
