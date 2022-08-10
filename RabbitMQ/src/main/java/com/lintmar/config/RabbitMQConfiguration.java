package com.lintmar.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LintMar
 * @date 2022/8/9
 **/
@Configuration
public class RabbitMQConfiguration {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Bean("directExchange")
    public Exchange exchange() {
        return ExchangeBuilder.topicExchange("amq.topic").build();
    }

    @Bean("fanoutExchange")
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("amq.fanout").build();
    }

    @Bean("topicExchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("amq.topic").build();
    }

    @Bean("headersExchange")
    public HeadersExchange headersExchange() {
        return ExchangeBuilder.headersExchange("amq.headers").build();
    }

    @Bean("directDlExchange")
    public Exchange dlExchange() {
        return ExchangeBuilder.directExchange("dlx.direct").build();
    }

    @Bean("testQueue")
    public Queue queue() {
        return QueueBuilder.nonDurable("test")
                .deadLetterExchange("dlx.direct")
                .deadLetterRoutingKey("dl-test")
                .ttl(60000)
                .maxLength(10)
                .build();
    }

    @Bean("testDlQueue")
    public Queue dlQueue() {
        return QueueBuilder.nonDurable("dl-test").build();
    }

    @Bean("demoQueue1")
    public Queue demo1() {
        return QueueBuilder.nonDurable("demo1").build();
    }

    @Bean("demoQueue2")
    public Queue demo2() {
        return QueueBuilder.nonDurable("demo2").build();
    }

    @Bean("binding1")
    public Binding binding1(@Qualifier("directExchange") Exchange exchange, @Qualifier("testQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queue.getName() + 1)
                .noargs();
    }

    @Bean("bindingTopic")
    public Binding bindingTopic(@Qualifier("topicExchange") Exchange exchange, @Qualifier("testQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("#." + queue.getName() + ".*")
                .noargs();
    }

    @Bean("bindingHeaders")
    public Binding bindingHeaders(@Qualifier("headersExchange") HeadersExchange exchange, @Qualifier("testQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .where("test").matches("Hello");
    }

    @Bean("dlBinding")
    public Binding dlBinding(@Qualifier("directDlExchange") Exchange exchange, @Qualifier("testDlQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queue.getName())
                .noargs();
    }

    @Bean("bindingFanout1")
    public Binding bindingFanout1(@Qualifier("fanoutExchange") Exchange exchange, @Qualifier("demoQueue1") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queue.getName())
                .noargs();
    }

    @Bean("bindingFanout2")
    public Binding bindingFanout2(@Qualifier("fanoutExchange") Exchange exchange, @Qualifier("demoQueue2") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(queue.getName())
                .noargs();
    }

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("containerFactory")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setPrefetchCount(1);
        return factory;
    }
}
