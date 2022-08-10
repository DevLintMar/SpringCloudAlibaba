package com.lintmar.listener;

import com.lintmar.entity.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@Slf4j
@Component
public class ConsumeListener {
    @RabbitListener(queues = "test", messageConverter = "jacksonConverter", containerFactory = "containerFactory", concurrency = "2")
    public String consume1(Msg msg) {
        log.info("[1]用户发送信息: " + msg);
        return "[1]服务器收到信息: " + msg;
    }

    @RabbitListener(queues = "test", messageConverter = "jacksonConverter", containerFactory = "containerFactory")
    public String consume2(Msg msg) {
        log.info("[2]用户发送信息: " + msg);
        return "[2]服务器收到信息: " + msg;
    }

    @RabbitListener(queues = "dl-test", messageConverter = "jacksonConverter")
    public void dlConsume(Msg msg) {
        log.info("死信队列收到信息: " + msg);
    }

    @RabbitListener(queues = "demo1", messageConverter = "jacksonConverter")
    public String demoConsume1(Msg msg) {
        String result = "demo1队列收到消息: " + msg;
        log.info(result);
        return result;
    }

    @RabbitListener(queues = "demo2", messageConverter = "jacksonConverter")
    public String demoConsume2(Msg msg) {
        String result = "demo2队列收到消息: " + msg;
        log.info(result);
        return result;
    }
}
