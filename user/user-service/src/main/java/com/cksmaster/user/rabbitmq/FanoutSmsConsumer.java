package com.cksmaster.user.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: cks
 * @Date: Created by 2018/11/14 10:45
 * @Package: com.cksmaster.user.rabbitmq
 * @Description: 邮件队列
 */
@Component
@RabbitListener(queues = "fanout_sms_queue")
public class FanoutSmsConsumer {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("短信消费者获取生产者消息msg:" + msg);
    }
}
