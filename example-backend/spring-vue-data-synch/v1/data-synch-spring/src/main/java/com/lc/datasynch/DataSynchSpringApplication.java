package com.lc.datasynch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataSynchSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSynchSpringApplication.class, args);
    }

}
