package com.demo.demoapi.service;

import com.demo.demoapi.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ProductTest {

    @Test
    void shouldUpdateProductWhenPriceIsValid() {
        Product product =
                new Product("Laptop", "Gaming laptop", BigDecimal.valueOf(1000));

        product.update("Updated", "Updated desc", BigDecimal.valueOf(2000));

        assertThat(product.getName()).isEqualTo("Updated");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(2000));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZeroOrNegative() {
        Product product =
                new Product("Laptop", "Gaming laptop", BigDecimal.valueOf(1000));

        assertThatThrownBy(() ->
                product.update("Updated", "Desc", BigDecimal.ZERO)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price must be positive");
    }
}
