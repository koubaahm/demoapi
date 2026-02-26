package com.demo.demoapi.controller;

import com.demo.demoapi.dto.ProductCreateRequest;
import com.demo.demoapi.dto.ProductResponse;
import com.demo.demoapi.dto.ProductUpdateRequest;
import com.demo.demoapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest productRequest) {
        ProductResponse product =productService.create(productRequest);
        return ResponseEntity.created(URI.create("/products/" + product.id()))
                .body(product);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductResponse product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest productRequest
    ) {
        ProductResponse product = productService.update(id, productRequest);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
