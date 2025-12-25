package com.sds.x6_order.model;

import java.math.BigDecimal;

public record OrderItem(
        Long id,
        Long orderId,
        Long productId,
        int quantity,
        BigDecimal price
) {}