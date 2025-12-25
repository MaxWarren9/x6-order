package com.sds.x6_order.repository;

import com.sds.x6_order.model.Order;
import com.sds.x6_order.model.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public Order insert(Order order) {
        Long orderId = jdbc.queryForObject(
                """
                INSERT INTO x6_order.orders (user_id, total_price, status)
                VALUES (:userId, :totalPrice, :status)
                RETURNING id
                """,
                new MapSqlParameterSource()
                        .addValue("userId", order.userId())
                        .addValue("totalPrice", order.totalPrice())
                        .addValue("status", order.status().name()),
                Long.class
        );

        for (OrderItem item : order.items()) {
            jdbc.update(
                    """
                    INSERT INTO x6_order.order_item (order_id, product_id, quantity, price)
                    VALUES (:orderId, :productId, :quantity, :price)
                    """,
                    new MapSqlParameterSource()
                            .addValue("orderId", orderId)
                            .addValue("productId", item.productId())
                            .addValue("quantity", item.quantity())
                            .addValue("price", item.price())
            );
        }

        return new Order(orderId, order.userId(), order.items(), order.totalPrice(), order.status());
    }
}
