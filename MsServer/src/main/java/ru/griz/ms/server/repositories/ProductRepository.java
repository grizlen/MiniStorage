package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
