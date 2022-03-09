package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.BuyItem;

import java.util.List;

public interface BuyItemRepository extends JpaRepository<BuyItem, Long> {
    List<BuyItem> findAllByDocId(Long docId);
}
