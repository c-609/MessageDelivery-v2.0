package cn.tiger;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageDeliveryApplicationTest {
    /*@Autowired
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
    }*/
}
