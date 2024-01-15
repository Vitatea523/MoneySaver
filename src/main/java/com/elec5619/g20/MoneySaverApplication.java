package com.elec5619.g20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MoneySaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneySaverApplication.class, args);
    }

}
