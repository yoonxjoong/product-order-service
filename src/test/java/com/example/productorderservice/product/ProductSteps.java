package com.example.productorderservice.product;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.MediaType;

public class ProductSteps {

    public static ExtractableResponse<Response> 상품등록요청(
            AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    public static AddProductRequest 상품조회요청_생성() {
        final String name = "상품명";
        final Long price = 1000L;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

    public static ExtractableResponse<Response> 상품조회요청(Long productId) {
        return RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then()
                .log().all()
                .extract();
    }
}