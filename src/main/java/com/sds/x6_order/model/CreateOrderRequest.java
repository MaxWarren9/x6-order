package com.sds.x6_order.model;

import java.util.Set;

public record CreateOrderRequest(
    Long userId,
    Set<CreateOrderItem> items
) {}
