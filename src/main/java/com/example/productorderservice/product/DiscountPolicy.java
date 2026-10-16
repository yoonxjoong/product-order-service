package com.example.productorderservice.product;

public enum DiscountPolicy {
    NONE {
        @Override
        public Long applyDiscount(Long price) {
            return 0L;
        }
    },

    FIX_1000_AMT {
        @Override
        public Long applyDiscount(Long price) {
            return price - 1000L < 0 ? 0 : price - 1000;
        }
    };


    public abstract Long applyDiscount(final Long price);
}
