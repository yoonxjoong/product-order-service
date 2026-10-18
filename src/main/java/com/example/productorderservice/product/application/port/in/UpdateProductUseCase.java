package com.example.productorderservice.product.application.port.in;

public interface UpdateProductUseCase {

    void updateProduct(Long productId, UpdateProductRequest request);
}
