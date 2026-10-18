package com.example.productorderservice.product.adapter.in.web;

import com.example.productorderservice.product.domain.DiscountPolicy;
import org.springframework.util.Assert;

record GetProductResponse(long id, String name, Long price, DiscountPolicy discountPolicy) {

    GetProductResponse {
        Assert.hasText(name, "상품명은 필수 입니다.");
    }
}
