package br.com.biot_admin.biot_admin;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class BIoTAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BIoTAdminApplication.class, args);
    }
}