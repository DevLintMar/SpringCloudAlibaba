package com.lintmar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author LintMar
 * @date 2022/8/10
 **/
@Slf4j
@Component
public class MessageConsumer {
    @Bean("stream")
    public Consumer<String> consumer() {
        return log::info;
    }
}
