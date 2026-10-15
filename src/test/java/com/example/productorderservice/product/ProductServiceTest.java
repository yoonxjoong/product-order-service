package com.example.productorderservice.product;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class ProductServiceTest {

    private ProductService productService;

    private StubProductPort productPort = new StubProductPort();

    @BeforeEach
    void setUp() {
        productService = new ProductService(productPort);
    }

    @Test
    void 상품수정요청() {
        final long productId = 1L;
        final Product product = new Product("상품명", 1000L, DiscountPolicy.NONE);
        UpdateProductRequest request = new UpdateProductRequest("상품수정", 1000L, DiscountPolicy.NONE);

        productPort.product = product;

        productService.updateProduct(productId, request);

        assertThat(product.getName()).isEqualTo("상품수정");
        assertThat(product.getPrice()).isEqualTo(1000L);

    }

    private static class StubProductPort implements ProductPort {

        public Product product;

        @Override
        public void save(Product product) {

        }

        @Override
        public Product getProduct(long productId) {
            return product;
        }
    }
}
