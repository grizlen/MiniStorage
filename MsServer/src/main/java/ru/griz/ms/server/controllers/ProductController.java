package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.griz.ms.server.entities.Product;
import ru.griz.ms.server.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getByID(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/")
    public Product postProduct(@RequestBody Product product) {
        if (product.getName().isEmpty()) {
            return null;
        }
        return productService.save(product);
    }
}
