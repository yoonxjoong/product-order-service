package com.example.productorderservice.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(),
                request.discountPolicy());

        productPort.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProduct(final long productId) {
        return productPort.getProduct(productId);
    }

    @Transactional
    public void updateProduct(final Long productId, final UpdateProductRequest request) {
        final Product product = productPort.getProduct(productId);

        product.update(request.name(), request.price(), request.discountPolicy());

        productPort.save(product);
    }
}
