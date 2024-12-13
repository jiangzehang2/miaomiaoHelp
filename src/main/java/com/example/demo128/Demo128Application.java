package com.example.demo128;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@MapperScan("com.example.demo128.mapper")
@SpringBootApplication
public class Demo128Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo128Application.class, args);
    }

}
