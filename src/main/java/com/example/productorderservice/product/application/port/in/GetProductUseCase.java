package com.example.productorderservice.product.application.port.in;

import com.example.productorderservice.product.domain.Product;
import java.util.List;

public interface GetProductUseCase {

    Product getProduct(long productId);

    List<Product> getProducts(int page, int size);
}
