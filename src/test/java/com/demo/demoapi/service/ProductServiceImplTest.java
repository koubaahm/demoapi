package com.demo.demoapi.service;

import com.demo.demoapi.domain.Product;
import com.demo.demoapi.dto.ProductCreateRequest;
import com.demo.demoapi.dto.ProductUpdateRequest;
import com.demo.demoapi.exception.ProductNotFoundException;
import com.demo.demoapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldCreateProduct() {
        ProductCreateRequest request =
                new ProductCreateRequest("Laptop", "Gaming laptop", BigDecimal.valueOf(1500));

        Product savedProduct =
                new Product("Laptop", "Gaming laptop", BigDecimal.valueOf(1500));

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        var response = productService.create(request);

        assertThat(response.name()).isEqualTo("Laptop");
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldReturnProductById() {
        Product product =
                new Product("Laptop", "Gaming laptop", BigDecimal.valueOf(1500));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var response = productService.getById(1L);

        assertThat(response.name()).isEqualTo("Laptop");
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getById(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    void shouldUpdateProduct() {
        Product product =
                new Product("Old", "Old desc", BigDecimal.valueOf(1000));

        ProductUpdateRequest request =
                new ProductUpdateRequest("Updated", "Updated desc", BigDecimal.valueOf(2000));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var response = productService.update(1L, request);

        assertThat(response.name()).isEqualTo("Updated");
        assertThat(product.getName()).isEqualTo("Updated");
    }

    @Test
    void shouldDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.delete(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonExistingProduct() {
        when(productRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> productService.delete(1L))
                .isInstanceOf(ProductNotFoundException.class);
    }
}