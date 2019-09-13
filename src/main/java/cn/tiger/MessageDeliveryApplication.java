package cn.tiger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("cn.tiger.mapper")
@EnableAsync //开启异步
@EnableRabbit //开区基于主借的rabbitMq
public class MessageDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageDeliveryApplication.class,args);
    }
}
