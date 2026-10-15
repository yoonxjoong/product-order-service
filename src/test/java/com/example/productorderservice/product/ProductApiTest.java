package com.example.productorderservice.product;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.productorderservice.ApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

class ProductApiTest extends ApiTest {

    @Test
    void 상품등록() {

        final var request = ProductSteps.상품조회요청_생성();

        final var response = ProductSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }

    @Test
    void 상품조회() {
        // 상품 등록
        ProductSteps.상품등록요청(ProductSteps.상품조회요청_생성());

        final Long productId = 1L;

        // 상품 조회
        final var response = ProductSteps.상품조회요청(productId);

        // 상품 검증
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

}
