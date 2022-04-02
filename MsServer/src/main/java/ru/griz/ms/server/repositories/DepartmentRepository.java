package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
