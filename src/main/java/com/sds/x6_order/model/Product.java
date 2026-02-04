package com.sds.x6_order.model;

import java.math.BigDecimal;

public record Product(
        Long id,
        BigDecimal price
) {}