package com.cksmaster.common;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

//@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
@EnableDubboConfiguration
@EnableAsync(proxyTargetClass = true)
public class StartCommon {

	public static void main(String[] args) {
		SpringApplication.run(StartCommon.class, args);
	}
}
