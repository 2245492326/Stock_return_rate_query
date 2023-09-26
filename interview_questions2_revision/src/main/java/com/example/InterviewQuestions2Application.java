package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class InterviewQuestions2Application {

    public static void main(String[] args) {
        SpringApplication.run(InterviewQuestions2Application.class, args);
    }
}
