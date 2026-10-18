package com.example.productorderservice.product.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import com.example.productorderservice.product.application.port.in.AddProductRequest;
import com.example.productorderservice.product.application.port.in.UpdateProductRequest;
import com.example.productorderservice.product.application.port.out.ProductPort;
import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ProductServiceTest extends ApiTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품수정요청() {
        AddProductRequest addProductRequest = ProductSteps.상품추가_요청();
        productService.addProduct(addProductRequest);

        Product product = productService.getProduct(1L);

        final UpdateProductRequest request =
                new UpdateProductRequest("상품수정", 1000L, DiscountPolicy.NONE);

        productService.updateProduct(product.getId(), request);

        Product updatedProduct = productService.getProduct(1L);

        assertThat(updatedProduct.getName()).isEqualTo("상품수정");
        assertThat(updatedProduct.getPrice()).isEqualTo(1000L);
    }

}
