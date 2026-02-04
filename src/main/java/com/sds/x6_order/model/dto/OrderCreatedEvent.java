package com.sds.x6_order.model.dto;
import com.sds.x6_order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private Set<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {

        private Long productId;
        private Integer quantity;
        private BigDecimal price;
    }

    public static OrderCreatedEvent from(Order order) {

        Set<Item> items = order.items().stream()
                               .map(i -> new Item(
                                       i.productId(),
                                       i.quantity(),
                                       i.price()
                               ))
                               .collect(Collectors.toSet());

        return new OrderCreatedEvent(
                order.id(),
                order.userId(),
                order.totalPrice(),
                items
        );
    }
}