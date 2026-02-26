package com.demo.demoapi.service;

import com.demo.demoapi.dto.ProductCreateRequest;
import com.demo.demoapi.dto.ProductResponse;
import com.demo.demoapi.dto.ProductUpdateRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {

    ProductResponse create(ProductCreateRequest request);
    ProductResponse getById(Long id);
    Page<ProductResponse> getAll(Pageable pageable);
    ProductResponse update(Long id, ProductUpdateRequest request);
    void delete(Long id);
}
