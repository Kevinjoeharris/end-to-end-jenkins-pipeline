package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldReturnAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty(), "Product list should not be empty");
    }

    @Test
    void shouldFindProductById() {
        Optional<Product> product = productService.getProductById(1L);
        assertTrue(product.isPresent(), "Product with id=1 should exist");
        assertEquals("Laptop", product.get().getName());
    }

    @Test
    void shouldCreateNewProduct() {
        Product newProduct = new Product(null, "Monitor", "4K display", 399.99);
        Product created = productService.createProduct(newProduct);
        assertNotNull(created.getId(), "Created product should have an ID");
        assertEquals("Monitor", created.getName());
    }

    @Test
    void shouldReturnEmptyForUnknownId() {
        Optional<Product> product = productService.getProductById(999L);
        assertTrue(product.isEmpty(), "Should return empty for unknown ID");
    }

    @Test
    void shouldDeleteProduct() {
        Product temp = productService.createProduct(new Product(null, "Temp", "To be deleted", 1.0));
        boolean deleted = productService.deleteProduct(temp.getId());
        assertTrue(deleted, "Delete should return true");
        assertTrue(productService.getProductById(temp.getId()).isEmpty());
    }
}
