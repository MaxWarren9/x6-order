package com.sds.x6_order.service;

import com.sds.x6_order.client.ProductClient;
import com.sds.x6_order.exception.BadRequestException;
import com.sds.x6_order.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExternalProductService {

    private final ProductClient productClient;

    public Product getProduct(Long id) {
        return productClient.getProduct(id);
    }

    public void checkProductsExist(List<Long> productIds) {
        boolean exists = productClient.areProductsAvailable(productIds);
        if (!exists) {
            throw new BadRequestException("One or more products do not exist");
        }
    }
}