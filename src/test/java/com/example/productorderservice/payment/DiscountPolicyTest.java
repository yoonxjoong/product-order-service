package com.example.productorderservice.payment;

import com.example.productorderservice.product.DiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiscountPolicyTest {

    @Test
    void noneDiscountPolicy() {
        final Long price = 1000L;
        final Long discountedPrice = DiscountPolicy.NONE.applyDiscount(price);

        Assertions.assertThat(price).isEqualTo(discountedPrice);
    }

    @Test
    void discountedPolicy() {
        final Long price = 2000L;
        final Long discountedPrice;
    }
}
