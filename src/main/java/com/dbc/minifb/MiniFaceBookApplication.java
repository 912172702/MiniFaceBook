package com.dbc.minifb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
public class MiniFaceBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniFaceBookApplication.class, args);
    }
}
