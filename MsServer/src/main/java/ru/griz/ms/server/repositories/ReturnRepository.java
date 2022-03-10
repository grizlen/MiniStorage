package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.ms.server.entities.ReturnHeader;

@Repository
public interface ReturnRepository extends JpaRepository<ReturnHeader, Long> {
}
