package lc.ws_mq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @RequestMapping("/send")
    public void SennerMsg(String msg){
        amqpTemplate.convertAndSend("exchange","a",msg);
    }

    @Autowired
    public MyWebSocketHandler handler;

    @RabbitListener(queues = "queue")
    public void Recive(String msg) throws Exception{
        handler.sendMsgToJsp(new TextMessage(msg), "A");
    }


}


