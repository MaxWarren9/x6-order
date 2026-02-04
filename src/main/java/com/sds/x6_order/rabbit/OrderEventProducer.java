package com.sds.x6_order.rabbit;

import com.sds.x6_order.config.RabbitConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfigProperties properties;

    public void send(Object message) {

        rabbitTemplate.convertAndSend(
                properties.getExchange(),
                properties.getQueue(),
                message
        );
    }
}