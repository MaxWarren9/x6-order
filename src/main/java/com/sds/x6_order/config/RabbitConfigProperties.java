package com.sds.x6_order.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.rabbitmq")
public class RabbitConfigProperties {
    private String queue;
    private String exchange;
}
