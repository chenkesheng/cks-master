package com.cksmaster.user.base;

import com.cksmaster.core.interceptor.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * rest适配器
 * Author: cks
 * Date:  17/7/8 下午10:42
 */
@Configuration
@EnableWebMvc
public class WebConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CorsInterceptor());
        registry.addInterceptor(new AuthInterceptor());
        registry.addInterceptor(new AuthorizationInterceptor());
//        registry.addInterceptor(new VisitLogInterceptor());
    }


    @Bean
    public MessageConverter messageConverter() {
        return new MessageConverter();
    }
}