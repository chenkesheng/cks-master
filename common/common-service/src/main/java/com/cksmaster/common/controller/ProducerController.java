package com.cksmaster.common.controller;

import com.cksmaster.common.rabbitmq.FanoutProducer;
import com.cksmaster.core.annotation.NotLogin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cks
 * @Date: Created by 2018/11/14 10:32
 * @Package: com.cksmaster.common.controller
 * @Description:
 */
@RestController("/")
public class ProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @NotLogin
    @GetMapping("/sendFanout")
    public String sendFanout(@RequestParam("queueName") String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }
}
