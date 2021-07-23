package com.sswu.crople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CropleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CropleApplication.class, args);
    }

}
