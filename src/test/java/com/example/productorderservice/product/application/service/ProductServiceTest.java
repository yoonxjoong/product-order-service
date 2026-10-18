package com.example.productorderservice.product.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import com.example.productorderservice.product.application.port.in.AddProductRequest;
import com.example.productorderservice.product.application.port.in.UpdateProductRequest;
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

    @Test
    void 상품조회_페이징() {
        productService.addProduct(ProductSteps.상품추가_요청());
        productService.addProduct(ProductSteps.상품추가_요청());
        productService.addProduct(ProductSteps.상품추가_요청());

        assertThat(productService.getProducts(0, 2)).hasSize(2);
        assertThat(productService.getProducts(1, 2)).hasSize(1);
    }

    @Test
    void 상품목록조회_유효성체크() {
        assertThatThrownBy(() -> productService.getProducts(-1, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("페이지 번호는 0 이상이어야 합니다.");
        assertThatThrownBy(() -> productService.getProducts(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("페이지 크기는 1 이상 100 이하여야 합니다.");
        assertThatThrownBy(() -> productService.getProducts(0, 101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("페이지 크기는 1 이상 100 이하여야 합니다.");
    }
}
