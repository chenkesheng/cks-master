package com.cksmaster.common;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication(scanBasePackages = "com.cksmaster.common.dubbo")
@EnableDubboConfiguration
public class StartCommon {

	public static void main(String[] args) {
		SpringApplication.run(StartCommon.class, args);
	}
}
