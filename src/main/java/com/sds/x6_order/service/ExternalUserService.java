package com.sds.x6_order.service;

import com.sds.x6_order.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExternalUserService {

    private final UserClient userClient;

    public void checkUser(Long id) {
        userClient.checkUserExists(id);
    }
}
