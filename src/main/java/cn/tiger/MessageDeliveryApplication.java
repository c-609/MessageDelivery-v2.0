package cn.tiger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tiger.mapper")
public class MessageDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageDeliveryApplication.class,args);
    }
}
