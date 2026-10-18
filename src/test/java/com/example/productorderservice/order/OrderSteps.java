package com.example.productorderservice.order;

import com.example.productorderservice.order.application.port.in.CreateOrderRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

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

    public static CreateOrderRequest 상품주문요청_생성() {
        final Long productId = 1L;
        final int quantity = 2;
        return new CreateOrderRequest(productId, quantity);
    }
}
