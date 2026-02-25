package com.demo.demoapi.service;

import com.demo.demoapi.domain.Product;
import com.demo.demoapi.dto.ProductCreateRequest;
import com.demo.demoapi.dto.ProductResponse;
import com.demo.demoapi.dto.ProductUpdateRequest;
import com.demo.demoapi.mapper.ProductMapper;
import com.demo.demoapi.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest productRequest) {
        Product product = ProductMapper.toEntity(productRequest);
        Product productSaved = productRepository.save(product);
        return ProductMapper.toResponse(productSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("product not found with id: " + id));
        return ProductMapper.toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(ProductMapper::toResponse).toList();
    }

    @Override
    public ProductResponse update(Long id, ProductUpdateRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("product not found with id: " + id));
        product.update(
                productRequest.name(),
                productRequest.description(),
                productRequest.price()
        );
        return ProductMapper.toResponse(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
