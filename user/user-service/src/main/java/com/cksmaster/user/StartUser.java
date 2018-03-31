package com.cksmaster.user;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author cks
 */
//@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
@EnableDubboConfiguration
public class StartUser {

    public static void main(String[] args) {
        SpringApplication.run(StartUser.class, args);
    }

}