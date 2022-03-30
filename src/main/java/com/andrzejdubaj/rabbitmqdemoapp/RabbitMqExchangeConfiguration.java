package com.andrzejdubaj.rabbitmqdemoapp;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqExchangeConfiguration {

    @Bean
    Exchange exampleExchange() {
        return new TopicExchange("ExampleExchange");
    }
}
