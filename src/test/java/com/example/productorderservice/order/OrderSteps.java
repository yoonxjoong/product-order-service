package com.example.productorderservice.order;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.jspecify.annotations.NonNull;

public class OrderSteps {

    public static ExtractableResponse<Response> 상품주문요청(CreateOrderRequest request) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/orders")
                .then()
                .log().all().extract();
    }

    public static @NonNull CreateOrderRequest 상품주문요청_생성() {
        Long productId = 1L;
        int quantity = 2;
        final var request = new CreateOrderRequest(productId, quantity);
        return request;
    }
}
