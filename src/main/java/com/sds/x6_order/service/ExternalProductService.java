package com.sds.x6_order.service;

import com.sds.x6_order.client.ProductClient;
import com.sds.x6_order.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExternalProductService {

    private final ProductClient productClient;

    public Product getProduct(Long id) {
        return productClient.getProduct(id);
    }
}