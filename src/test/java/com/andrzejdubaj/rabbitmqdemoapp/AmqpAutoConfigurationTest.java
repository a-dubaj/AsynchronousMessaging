package com.andrzejdubaj.rabbitmqdemoapp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AmqpAutoConfigurationTest {

    @MockBean(name = "messageListenerContainer")
    MessageListenerContainer messageListenerContainer;

    @Autowired(required = false)
    RabbitTemplate rabbitTemplate;

    @Autowired(required = false)
    ConnectionFactory connectionFactory;

    @Test
    void rabbitTemplateBeanExists() {
        assertThat(rabbitTemplate).isNotNull();
    }

    @Test
    void connectionFactoryBeanExists() {
        assertThat(connectionFactory).isNotNull();
    }
}