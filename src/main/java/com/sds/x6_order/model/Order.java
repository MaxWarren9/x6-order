package com.sds.x6_order.model;

import com.sds.x6_order.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

public record Order(
        Long id,
        Long userId,
        Set<OrderItem> items,
        BigDecimal totalPrice,
        OrderStatus status
) {}