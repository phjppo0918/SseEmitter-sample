package com.example.sseemittertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SseEmitterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseEmitterTestApplication.class, args);
    }

}
