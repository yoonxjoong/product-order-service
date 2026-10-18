package com.example.productorderservice.product.adapter.in.web;

import com.example.productorderservice.product.domain.DiscountPolicy;
import com.example.productorderservice.product.domain.Product;
import org.springframework.util.Assert;

record GetProductResponse(long id, String name, Long price, DiscountPolicy discountPolicy) {

    GetProductResponse {
        Assert.hasText(name, "상품명은 필수 입니다.");
    }

    public static GetProductResponse from(final Product product) {
        return new GetProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy());
    }
}
