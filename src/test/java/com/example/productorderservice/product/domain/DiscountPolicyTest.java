package com.example.productorderservice.product.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountPolicyTest {

    @Test
    void noneDiscountPolicy() {
        final Long price = 1000L;
        final Long discountedPrice = DiscountPolicy.NONE.applyDiscount(price);

        Assertions.assertThat(price).isEqualTo(price - discountedPrice);
    }

    @Test
    void discountedPolicy() {
        final Long price = 2000L;
        final Long discountedPrice = DiscountPolicy.FIX_1000_AMT.applyDiscount(price);

        Assertions.assertThat(price).isNotEqualTo(price - discountedPrice);
    }
}
