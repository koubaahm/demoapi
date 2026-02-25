package com.demo.demoapi.mapper;

import com.demo.demoapi.domain.Product;
import com.demo.demoapi.dto.ProductCreateRequest;
import com.demo.demoapi.dto.ProductResponse;

public final class ProductMapper {

    public ProductMapper() {
    }

    public static Product toEntity(ProductCreateRequest request) {
        return new Product(
                request.name(),
                request.description(),
                request.price()
        );
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }
}
