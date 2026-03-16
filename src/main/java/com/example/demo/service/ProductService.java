package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // In-memory store (no DB needed for this demo)
    private final List<Product> products = new ArrayList<>(List.of(
        new Product(1L, "Laptop",     "High-performance laptop",  999.99),
        new Product(2L, "Keyboard",   "Mechanical keyboard",       79.99),
        new Product(3L, "Mouse",      "Wireless ergonomic mouse",  49.99)
    ));

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        long nextId = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1;
        product.setId(nextId);
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(Long id, Product updated) {
        return getProductById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setPrice(updated.getPrice());
            return existing;
        });
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
