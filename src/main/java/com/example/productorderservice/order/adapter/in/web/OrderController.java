package com.example.productorderservice.order.adapter.in.web;

import com.example.productorderservice.order.application.port.in.CreateOrderRequest;
import com.example.productorderservice.order.application.port.in.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    OrderController(final CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping()
    public ResponseEntity<Void> createOrder(@RequestBody final CreateOrderRequest request) {
        createOrderUseCase.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
