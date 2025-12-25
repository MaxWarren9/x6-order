package com.sds.x6_order;

import com.sds.x6_order.enums.OrderStatus;
import com.sds.x6_order.model.CreateOrderItem;
import com.sds.x6_order.model.CreateOrderRequest;
import com.sds.x6_order.model.Order;
import com.sds.x6_order.model.Product;
import com.sds.x6_order.repository.OrderRepository;
import com.sds.x6_order.service.ExternalProductService;
import com.sds.x6_order.service.ExternalUserService;
import com.sds.x6_order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ExternalUserService userService;

    @Mock
    ExternalProductService productService;

    @InjectMocks
    OrderService orderService;

    @Test
    void createOrder_success() {
        CreateOrderRequest request = new CreateOrderRequest(
                1L,
                Set.of(new CreateOrderItem(10L, 2))
        );

        Product product = new Product(10L, BigDecimal.valueOf(100));
        when(productService.getProduct(10L)).thenReturn(product);

        when(orderRepository.insert(any(Order.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Order order = orderService.create(request);

        assertNotNull(order);
        assertEquals(1L, order.userId());
        assertEquals(BigDecimal.valueOf(200), order.totalPrice());
        assertEquals(OrderStatus.CREATED, order.status());

        verify(userService).checkUser(1L);
        verify(productService).getProduct(10L);
        verify(orderRepository).insert(any(Order.class));
    }
}
