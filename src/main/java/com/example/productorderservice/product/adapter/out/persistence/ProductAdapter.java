package com.example.productorderservice.product.adapter.out.persistence;

import com.example.productorderservice.product.application.port.out.ProductPort;
import com.example.productorderservice.product.domain.Product;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductAdapter implements ProductPort {

    private final ProductRepository productRepository;

    ProductAdapter(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(final Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProduct(final long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Override
    public List<Product> getProducts(final int page, final int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageRequest).getContent();

    }
}
