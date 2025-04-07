package com.rapidshine.carwash.washerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WasherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WasherServiceApplication.class, args);
    }

}
