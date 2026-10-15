package com.example.productorderservice.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Test
    void 주문생성() {

        String name = "상품번호";
        Long price = 1000L;
        int quantity = 1;

        Order order = new Order(name, price, quantity);



    }

    private class Order {
        private final String name;
        private final Long price;
        private final int quantity;

        public Order(final String name, final Long price, final int quantity) {

            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }
}
