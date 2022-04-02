package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.griz.ms.server.dtos.DepartmentDTO;
import ru.griz.ms.server.dtos.DocSaleDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.SaleItemRepository;
import ru.griz.ms.server.repositories.SaleRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocSaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final DocumentService documentService;
    private final DepartmentService departmentService;
    private final ProductService productService;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    public List<DocSaleDTO> getAll() {
        return saleRepository.findAll().stream()
                .map(header -> {
                            SubDepartment subDepartment = departmentService.getByIdSubDepartment(header.getSubDepartmentId());
                            Department department = departmentService.getByIdDepartment(subDepartment.getDepartment());
                            return DocSaleDTO.builder()
                                    .id(header.getId())
                                    .date(header.getDate())
                                    .department(
                                            DepartmentDTO.builder()
                                                    .departmentId(department.getId())
                                                    .departmentName(department.getName())
                                                    .subDepartmentId(subDepartment.getId())
                                                    .subDepartmentName(subDepartment.getName())
                                                    .build()
                                    )
                                    .build();
                })
                .collect(Collectors.toList());
    }

    public DocSaleDTO getById(Long id) {
        SaleHeader header = saleRepository.findById(id).orElseThrow(documentNotFound(id));
        SubDepartment subDepartment = departmentService.getByIdSubDepartment(header.getSubDepartmentId());
        Department department = departmentService.getByIdDepartment(subDepartment.getDepartment());
        return DocSaleDTO.builder()
                .id(header.getId())
                .date(header.getDate())
                .department(
                        DepartmentDTO.builder()
                                .departmentId(department.getId())
                                .departmentName(department.getName())
                                .subDepartmentId(subDepartment.getId())
                                .subDepartmentName(subDepartment.getName())
                                .build()
                )
                .items(
                        getItems(header.getId()).stream()
                                .map(item ->
                                        DocSaleDTO.itemBuilder()
                                                .productId(item.getProductId())
                                                .productName(productService.getById(item.getProductId()).getName())
                                                .count(item.getCount())
                                                .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Transactional
    public DocSaleDTO save(DocSaleDTO doc) {
        if (doc.getId() != null) {
            saleItemRepository.deleteByDocId(doc.getId());
        }

        Document document = documentService.saveBuy(doc.getId());
        Long docId = document.getId();

        SaleHeader header = new SaleHeader();
        header.setId(docId);
        header.setDate(doc.getDate());
        saleRepository.save(header);

        List<SaleItem> saleItems = doc.getItems().stream()
                .map(i -> {
                    SaleItem item = new SaleItem();
                    item.setDocId(docId);
                    item.setId(null);
                    item.setProductId(i.getProductId());
                    item.setCount(i.getCount());
                    return item;
                })
                .collect(Collectors.toList());
        saleItemRepository.saveAll(saleItems);

        return getById(docId);
    }

    private List<SaleItem> getItems(long docId) {
        return saleItemRepository.findAllByDocId(docId);
    }
}
