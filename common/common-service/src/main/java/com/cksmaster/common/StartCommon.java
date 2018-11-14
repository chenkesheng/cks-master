package com.cksmaster.common;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

//@ImportResource("classpath*:applicationContext.xml")
@SpringBootApplication
@EnableDubboConfiguration
@EnableAsync(proxyTargetClass = true)
@MapperScan("com.cksmaster.common.mapper")
public class StartCommon {

	public static void main(String[] args) {
		SpringApplication.run(StartCommon.class, args);
	}
}
