package com.example.productorderservice.product.application.port.in;

import com.example.productorderservice.product.domain.Product;

public interface GetProductUseCase {

    Product getProduct(long productId);
}
