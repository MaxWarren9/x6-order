package com.sds.x6_order.client;

import com.sds.x6_order.controller.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient userClient;

    public UserClient(@Qualifier("ExtUserClient") RestClient userClient) {
        this.userClient = userClient;
    }

    public void checkUser(Long id) {
        try {
            userClient.get()
                      .uri("/api/v1/user/{id}", id)
                      .retrieve()
                      .toBodilessEntity();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("User not found: " + id);
            }
            throw e;
        }
    }
}