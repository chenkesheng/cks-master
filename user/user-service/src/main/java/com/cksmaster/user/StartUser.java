package com.cksmaster.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author cks
 */
@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
public class StartUser {

    public static void main(String[] args) {
        SpringApplication.run(StartUser.class, args);
    }

}