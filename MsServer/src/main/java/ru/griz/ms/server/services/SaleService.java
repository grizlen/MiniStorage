package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.SaleHeader;
import ru.griz.ms.server.entities.SaleItem;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.SaleItemRepository;
import ru.griz.ms.server.repositories.SaleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;

    public List<SaleHeader> getAll() {
        return saleRepository.findAll();
    }

    public SaleHeader getById(Long id) {
        return saleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("document: " + id + " not found.")
        );
    }

    public List<SaleItem> getDocItems(long docId) {
        return saleItemRepository.findAllByDocId(docId);
    }
}
