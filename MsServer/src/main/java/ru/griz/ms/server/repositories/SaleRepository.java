package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.ms.server.entities.BuyHeader;
import ru.griz.ms.server.entities.SaleHeader;

@Repository
public interface SaleRepository extends JpaRepository<SaleHeader, Long> {
}
