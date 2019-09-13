package cn.tiger;

import cn.tiger.entity.MessageEntity;
import cn.tiger.entity.ReadMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageDeliveryApplicationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads(){

//        自定义消息内容和消息头
//        rabbitTemplate.send();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setId(12);
        messageEntity.setSenderName("张三");
        //被默认的序列话发送出去
        rabbitTemplate.convertAndSend("exchange.direct","readMessage",messageEntity);
    }

    @Test
    public void receive(){
        Object readMessage = rabbitTemplate.receiveAndConvert("readMessage");
        System.out.println(readMessage.getClass());
        System.out.println(readMessage);
    }
}
