package com.example.productorderservice.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.productorderservice.ApiTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

class ProductApiTest extends ApiTest {

    @Test
    void 상품등록() {

        final var request = ProductSteps.상품추가_요청();

        final var response = ProductSteps.상품등록요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }

    @Test
    void 상품조회() {
        // 상품 등록
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        final Long productId = 1L;

        // 상품 조회
        final var response = ProductSteps.상품조회요청(productId);

        // 상품 검증
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getString("name")).isEqualTo("상품명");
    }

    @Test
    void 존재하지_않는_상품조회() {
        final var response = ProductSteps.상품조회요청(1L);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(response.asString()).isEqualTo("상품이 존재하지 않습니다.");
    }

    @Test
    void 상품목록_조회() {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        final var response = ProductSteps.상품목록조회_요청(0, 2);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.jsonPath().getList("")).hasSize(2);
    }

    @Test
    void 상품목록조회_잘못된_페이지_요청() {
        final var response = ProductSteps.상품목록조회_요청(-1, 10);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
        assertThat(response.asString()).isEqualTo("페이지 번호는 0 이상이어야 합니다.");
    }

}
