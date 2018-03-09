package com.cksmaster.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
public class StartCommon {

	public static void main(String[] args) {
		SpringApplication.run(StartCommon.class, args);
	}
}
