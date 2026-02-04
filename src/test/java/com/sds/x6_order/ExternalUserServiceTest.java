package com.sds.x6_order;

import com.sds.x6_order.client.UserClient;
import com.sds.x6_order.exception.NotFoundException;
import com.sds.x6_order.service.ExternalUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class ExternalUserServiceTest {

    @Mock
    UserClient userClient;

    @InjectMocks
    ExternalUserService service;

    @Test
    void checkUser_notFound() {
        doThrow(new NotFoundException("User not found: 1"))
                .when(userClient).checkUserExists(1L);

        assertThrows(NotFoundException.class, () -> service.checkUser(1L));
    }
}