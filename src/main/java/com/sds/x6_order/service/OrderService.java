package com.sds.x6_order.service;

import com.sds.x6_order.enums.OrderStatus;
import com.sds.x6_order.exception.BadRequestException;
import com.sds.x6_order.model.CreateOrderRequest;
import com.sds.x6_order.model.Order;
import com.sds.x6_order.model.OrderItem;
import com.sds.x6_order.model.Product;
import com.sds.x6_order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ExternalUserService userService;
    private final ExternalProductService productService;

    @Transactional
    public Order create(CreateOrderRequest req) {
        if (req.items() == null || req.items().isEmpty()) {
            throw new BadRequestException("Order must contain at least one item");
        }
        userService.checkUser(req.userId());

        Set<OrderItem> items = req.items().stream()
                                  .map(i -> {
                                      Product p = productService.getProduct(i.productId());
                                      if (i.quantity() <= 0) {
                                          throw new BadRequestException("Quantity must be > 0");
                                      }
                                      return new OrderItem(null, null, i.productId(), i.quantity(), p.price());
                                  })
                                  .collect(Collectors.toSet());

        BigDecimal total = items.stream()
                                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return orderRepository.insert(
                new Order(null, req.userId(), items, total, OrderStatus.CREATED)
        );
    }
}