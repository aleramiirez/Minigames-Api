package com.minijuegos.userfunctions.published;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQUsersProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.users.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQUsersProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQUsersProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUsersMessage(String auditingData) {
        LOGGER.info(String.format("Json message sent -> %s", auditingData));

        rabbitTemplate.convertAndSend(exchange, routingJsonKey, auditingData);
    }
}
