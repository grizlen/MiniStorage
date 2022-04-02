package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.dtos.DepartmentDTO;
import ru.griz.ms.server.entities.Department;
import ru.griz.ms.server.entities.Product;
import ru.griz.ms.server.entities.SubDepartment;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.DepartmentRepository;
import ru.griz.ms.server.repositories.ProductRepository;
import ru.griz.ms.server.repositories.SubDepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final SubDepartmentRepository subDepartmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getByIdDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("department with Id=" + id + " not found."));
        return department;
    }

    public Department saveDepartment(Department department) {
        if (department.getId() == null) {
            Optional<Department> optional = departmentRepository.findByName(department.getName());
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return departmentRepository.save(department);
    }

    public SubDepartment getByIdSubDepartment(Long id) {
        return subDepartmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("sub department with Id=" + id + " not found."));
    }
}
