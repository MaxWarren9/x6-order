package com.sds.x6_order.model;

public record CreateOrderItem(
        Long productId,
        int quantity
) {}