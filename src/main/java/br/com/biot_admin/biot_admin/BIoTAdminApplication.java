package br.com.biot_admin.biot_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BIoTAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BIoTAdminApplication.class, args);
    }
}