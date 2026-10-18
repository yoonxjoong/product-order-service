package com.example.productorderservice.product.adapter.in.web;

import com.example.productorderservice.product.application.port.in.AddProductRequest;
import com.example.productorderservice.product.application.port.in.AddProductUseCase;
import com.example.productorderservice.product.application.port.in.GetProductUseCase;
import com.example.productorderservice.product.application.port.in.UpdateProductRequest;
import com.example.productorderservice.product.application.port.in.UpdateProductUseCase;
import com.example.productorderservice.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductController {

    private final AddProductUseCase addProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    ProductController(final AddProductUseCase addProductUseCase,
            final GetProductUseCase getProductUseCase,
            final UpdateProductUseCase updateProductUseCase) {
        this.addProductUseCase = addProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @PostMapping()
    public ResponseEntity<Void> addProduct(@RequestBody final AddProductRequest request) {
        addProductUseCase.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable final Long productId) {
        final Product product = getProductUseCase.getProduct(productId);

        return ResponseEntity.ok(new GetProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy()));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable final Long productId,
            @RequestBody final UpdateProductRequest request) {
        updateProductUseCase.updateProduct(productId, request);

        return ResponseEntity.ok().build();
    }
}
