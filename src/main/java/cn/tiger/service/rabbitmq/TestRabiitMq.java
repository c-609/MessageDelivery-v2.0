package cn.tiger.service.rabbitmq;

import cn.tiger.entity.MessageEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestRabiitMq {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 将被被读的消息发送到rabbitMQ
     * @param messageEntity
     */
    @Async
    public void sendReadMessage(MessageEntity messageEntity){
        rabbitTemplate.convertAndSend("exchange.direct","readMessage",messageEntity);
    }

    /**
     * 接受rabbitMq的消息
     */
    @RabbitListener(queues = "readMessage")
    public void receive(/**Message message*/ MessageEntity messageEntity){
        System.out.println(messageEntity.getId());
        System.out.println(messageEntity.getSenderName());
//        System.out.println(message.getBody());
//        System.out.println(message.getMessageProperties());
    }
}
