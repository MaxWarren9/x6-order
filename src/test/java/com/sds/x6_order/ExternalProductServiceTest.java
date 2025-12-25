package com.sds.x6_order;

import com.sds.x6_order.client.ProductClient;
import com.sds.x6_order.model.Product;
import com.sds.x6_order.service.ExternalProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExternalProductServiceTest {

    @Mock
    ProductClient productClient;

    @InjectMocks
    ExternalProductService service;

    @Test
    void getProduct_ok() {
        Product product = new Product(
                1L,
                BigDecimal.valueOf(100)
        );

        when(productClient.getProduct(1L)).thenReturn(product);

        Product result = service.getProduct(1L);

        assertEquals(product, result);
    }
}
