package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.ms.server.entities.BuyHeader;

@Repository
public interface BuyRepository extends JpaRepository<BuyHeader, Long> {
}
