package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.BuyItem;
import ru.griz.ms.server.entities.SaleItem;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
    List<SaleItem> findAllByDocId(Long docId);
}
