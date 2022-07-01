package com.example.openbankpaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
        //(exclude = DataSourceAutoConfiguration.class)
public class OpenBankPayDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenBankPayDemoApplication.class, args);
    }

}
