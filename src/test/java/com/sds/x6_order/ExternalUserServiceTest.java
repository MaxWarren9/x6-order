package com.sds.x6_order;

import com.sds.x6_order.client.UserClient;
import com.sds.x6_order.controller.NotFoundException;
import com.sds.x6_order.service.ExternalUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalUserServiceTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    ExternalUserService service;

    @Test
    void checkUser_notFound() {
        doThrow(new NotFoundException("User not found: 1"))
                .when(userClient).checkUser(1L);

        assertThrows(NotFoundException.class, () -> service.checkUser(1L));
    }
}