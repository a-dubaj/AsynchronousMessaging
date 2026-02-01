package com.andrzejdubaj.rabbitmqdemoapp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RabbitMqDemoAppApplicationTests {

    @MockBean(name = "messageListenerContainer")
    MessageListenerContainer messageListenerContainer;

    @Test
    void contextLoads() {}
}