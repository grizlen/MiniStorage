package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.Product;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with Id=" + id + " not found."));
        return product;
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            Optional<Product> optional = productRepository.findByName(product.getName());
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return productRepository.save(product);
    }
}
