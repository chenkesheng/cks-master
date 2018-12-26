package com.cksmaster.user;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cks
 */
//@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
@EnableDubboConfiguration
@EnableSwagger2
public class StartUser {

    public static void main(String[] args) {
        SpringApplication.run(StartUser.class, args);
    }

}