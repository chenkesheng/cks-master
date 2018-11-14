package com.cksmaster.common.controller;

import com.cksmaster.common.rabbitmq.FanoutProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cks
 * @Date: Created by 2018/11/14 10:32
 * @Package: com.cksmaster.common.controller
 * @Description:
 */
@RestController
public class ProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendFanout")
    public String sendFanout(String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }
}
