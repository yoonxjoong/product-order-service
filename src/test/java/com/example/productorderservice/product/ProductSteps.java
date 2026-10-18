package com.example.productorderservice.product;

import com.example.productorderservice.product.application.port.in.AddProductRequest;
import com.example.productorderservice.product.domain.DiscountPolicy;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ProductSteps {

    public static AddProductRequest 상품추가_요청() {
        final String name = "상품명";
        final Long price = 1000L;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;

        return new AddProductRequest(name, price, discountPolicy);
    }

    public static ExtractableResponse<Response> 상품등록요청(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all()
                .extract();
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
