package ru.griz.ms.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.griz.ms.server.entities.SubDepartment;

public interface SubDepartmentRepository extends JpaRepository<SubDepartment, Long> {
}
