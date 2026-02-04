package com.sds.x6_order.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    private final RabbitConfigProperties properties;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public Queue testQueue() {
        return new Queue(properties.getQueue(), true // durable
        );
    }

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(properties.getExchange());
    }

    @Bean
    public Binding fiscalBinding(Queue testQueue, DirectExchange testExchange) {

        return BindingBuilder.bind(testQueue)
                             .to(testExchange)
                             .withQueueName();
    }


}
