package com.example.productorderservice.product.application.port.out;

import com.example.productorderservice.product.domain.Product;
import java.util.List;

public interface ProductPort {

    void save(Product product);

    Product getProduct(long productId);

    List<Product> getProducts(int page, int size);
}
