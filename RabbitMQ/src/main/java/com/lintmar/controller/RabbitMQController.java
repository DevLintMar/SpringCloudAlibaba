package com.lintmar.controller;

import com.lintmar.entity.Msg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@RestController
public class RabbitMQController {
    /*
    private static final ConnectionFactory FACTORY = new ConnectionFactory();

    static {
        FACTORY.setHost("192.168.154.130");
        FACTORY.setPort(5672);
        FACTORY.setUsername("rabbit");
        FACTORY.setPassword("rabbit");
        FACTORY.setVirtualHost("/test");
    }

    @RequestMapping("/supply")
    public void supplier(String message) {
        if (message == null || message.isEmpty()) throw new RuntimeException();
        try (Connection connection = FACTORY.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("test", false, false, false, null);
            channel.queueBind("test", "amq.direct", "test");
            channel.basicPublish("amq.direct", "test", null, message.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/consume")
    public String consumer() {
        String[] result = new String[1];
        try (Connection connection = FACTORY.newConnection()) {
            Channel channel = connection.createChannel();
            channel.basicConsume("test", false, (s, delivery) -> result[0] = new String(delivery.getBody()), s -> {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result[0];
    }
    */
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/supply/{key}/{type}/{message}")
    public String supply(@PathVariable("key") String key, @PathVariable("type") String type, @PathVariable("message") String message) {
        if (message == null || message.isEmpty()) throw new RuntimeException();
        Msg msg = new Msg();
        msg.setId(UUID.randomUUID().toString());
        msg.setMessage(message);
        return (String) rabbitTemplate.convertSendAndReceive("amq." + type, key, msg);
    }
}
