package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.ReturnItem;

import java.util.List;

public interface ReturnItemRepository extends JpaRepository<ReturnItem, Long> {
    List<ReturnItem> findAllByDocId(Long docId);
}
