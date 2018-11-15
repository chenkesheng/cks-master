package com.cksmaster.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: cks
 * @Date: Created by 2018/11/15 15:03
 * @Package: com.cksmaster.job
 * @Description: 定时器启动类
 */
@SpringBootApplication
public class StartJob {
    public static void main(String[] args) {
        SpringApplication.run(StartJob.class, args);
    }
}
