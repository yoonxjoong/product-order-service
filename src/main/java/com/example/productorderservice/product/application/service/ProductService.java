package com.example.productorderservice.product.application.service;

import com.example.productorderservice.product.application.port.in.AddProductRequest;
import com.example.productorderservice.product.application.port.in.AddProductUseCase;
import com.example.productorderservice.product.application.port.in.GetProductUseCase;
import com.example.productorderservice.product.application.port.in.UpdateProductRequest;
import com.example.productorderservice.product.application.port.in.UpdateProductUseCase;
import com.example.productorderservice.product.application.port.out.ProductPort;
import com.example.productorderservice.product.domain.Product;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
class ProductService implements AddProductUseCase, GetProductUseCase, UpdateProductUseCase {

    private static final int MAX_PAGE_SIZE = 100;

    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Override
    @Transactional
    public void addProduct(final AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(),
                request.discountPolicy());

        productPort.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(final long productId) {
        return productPort.getProduct(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts(final int page, final int size) {
        Assert.isTrue(page >= 0, "페이지 번호는 0 이상이어야 합니다.");
        Assert.isTrue(size >= 1 && size <= MAX_PAGE_SIZE,
                "페이지 크기는 1 이상 " + MAX_PAGE_SIZE + " 이하여야 합니다.");

        return productPort.getProducts(page, size);
    }

    @Override
    @Transactional
    public void updateProduct(final Long productId, final UpdateProductRequest request) {
        final Product product = productPort.getProduct(productId);

        product.update(request.name(), request.price(), request.discountPolicy());

        productPort.save(product);
    }
}
