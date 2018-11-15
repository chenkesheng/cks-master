package com.cksmaster.common.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: cks
 * @Date: Created by 2018/11/14 10:28
 * @Package: com.cksmaster.common.rabbitmq.config
 * @Description: mq的配置
 */
@Component
public class FanoutConfig {

    // 邮件队列
    private static String FANOUT_EMAIL_QUEUE = "fanout_email_queue";

    // 短信队列
    private static String FANOUT_SMS_QUEUE = "fanout_sms_queue";

    // fanout交换机
    private static String EXCHANGE_NAME = "fanoutExchange";

    // 1.定义队列邮件
    @Bean
    public Queue fanoutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    // 2.定义短信队列
    @Bean
    public Queue fanoutSmsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    // 3.fanout类型定义交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    // 3.队列与交换机绑定邮件队列
    @Bean
    Binding bindingExchangeEmail(@Qualifier("fanoutEmailQueue") Queue fanoutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }

    // 4.队列与交换机绑定短信队列
    @Bean
    Binding bindingExchangeSms(@Qualifier("fanoutSmsQueue") Queue fanoutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }
}
