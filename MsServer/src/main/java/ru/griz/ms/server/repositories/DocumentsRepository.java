package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.griz.ms.server.entities.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {
}
