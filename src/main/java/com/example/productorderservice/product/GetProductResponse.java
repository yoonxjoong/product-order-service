package com.example.productorderservice.product;

import org.springframework.util.Assert;

record GetProductResponse(long id, String name, Long price, DiscountPolicy discountPolicy) {

    GetProductResponse {
        Assert.notNull(id, "상품 ID는 필수입니다.");
        Assert.hasText(name, "상품명은 필수 입니다.");
    }
}
