package com.andrzejdubaj.rabbitmqdemoapp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class RabbitMqRoundTripIT {

    @Test
    void canSendAndReceive_usingRabbitJavaClient() throws Exception {
        Assumptions.assumeTrue(isPortOpen("localhost", 5672, Duration.ofMillis(250)),
                "RabbitMQ is not running on localhost:5672 - skipping integration test");

        String queue = "it.queue.roundtrip";
        String payload = "ping";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {

            channel.queueDeclare(queue, false, false, true, null);
            channel.basicPublish("", queue, null, payload.getBytes(StandardCharsets.UTF_8));

            var delivery = channel.basicGet(queue, true);
            assertThat(delivery).isNotNull();
            assertThat(new String(delivery.getBody(), StandardCharsets.UTF_8)).isEqualTo(payload);
        }
    }

    private static boolean isPortOpen(String host, int port, Duration timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), (int) timeout.toMillis());
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}