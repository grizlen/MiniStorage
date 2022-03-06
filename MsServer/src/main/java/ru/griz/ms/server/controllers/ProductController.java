package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
