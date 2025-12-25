package com.sds.x6_order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    @Bean("ExtUserClient")
    public RestClient userClient() {
        return RestClient.builder()
                         .baseUrl("http://localhost:8083")
                         .build();
    }

    @Bean("ExtProductClient")
    public RestClient productClient() {
        return RestClient.builder()
                         .baseUrl("http://localhost:8080")
                         .build();
    }
}
