package com.cksmaster.user.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.cksmaster.core.utils.HttpClientUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: cks
 * @Date: Created by 2018/11/14 10:34
 * @Package: com.cksmaster.user.rabbitmq
 * @Description: 监听邮件消息消费
 */
@Component
public class FanoutEmailConsumer {

    private static final String SMS_URL = "http://127.0.0.1:8010/sendEmail";

    @RabbitListener(queues = "fanout_email_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        System.out.println(Thread.currentThread().getName() + ",邮件消费者获取生产者消息msg:" + new String(message.getBody(), "UTF-8")
                        + ",messageId:" + message.getMessageProperties().getMessageId());
        // 手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "676638160@qq.com");
        JSONObject result = HttpClientUtils.httpPost(SMS_URL, jsonObject);
        if (result == null || !result.getString("code").equals("200")) {
            // 抛出异常,使用消息补偿机制,进行补偿!
            System.out.println("抛出异常,开始进行补偿消费消息...");
            throw new Exception("发送邮件失败,进行补偿!!!");
        }
        // 邮件消费成功... 通知队列服务器端删除该消息...
        channel.basicAck(deliveryTag, false);
    }
}
