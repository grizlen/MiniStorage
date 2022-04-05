package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.griz.ms.server.dtos.DocSaleDTO;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.mediators.DocSaleMediator;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DocSaleService {

    private final DocSaleMediator mediator;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    public List<DocSaleDTO> getAll() {
        return mediator.getAll();
    }

    public DocSaleDTO getById(Long id) {
        return mediator.getById(id);
    }

    @Transactional
    public DocSaleDTO save(DocSaleDTO doc) {
        return mediator.save(doc);
    }
}
