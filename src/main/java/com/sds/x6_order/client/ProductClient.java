package com.sds.x6_order.client;

import com.sds.x6_order.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductClient {

    private final RestClient productClient;

    public ProductClient(@Qualifier("ExtProductClient") RestClient productClient) {
        this.productClient = productClient;
    }


    public Product getProduct(Long id) {
        return productClient.get()
                            .uri("/api/v1/product/{id}", id)
                            .retrieve()
                            .body(Product.class);
    }
}