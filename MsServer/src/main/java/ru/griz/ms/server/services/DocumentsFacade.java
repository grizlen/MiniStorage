package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.dtos.DocSaleDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.ReturnItemRepository;
import ru.griz.ms.server.repositories.ReturnRepository;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class DocumentsFacade {

    private final DocumentService documentService;
    private final DocBuyService docBuyService;
    private final DocSaleService docSaleService;
    private final ReturnRepository returnRepository;
    private final ReturnItemRepository returnItemRepository;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    // Все документы
    public List<Document> getAllDocs() {
        return documentService.getAll();
    }

    public Document getByIdDoc(Long id) {
        return documentService.getById(id);
    }

    // Поступления
    public List<DocBuyDTO> getAllDocBuys() {
        return docBuyService.getAll();
    }

    public DocBuyDTO getByIdDocBuy(Long id) {
        return docBuyService.getById(id);
    }

    public DocBuyDTO saveDocBuy(DocBuyDTO doc) {
        return docBuyService.save(doc);
    }

    // Отгрузки
    public List<DocSaleDTO> getAllDocSales() {
        return docSaleService.getAll();
    }

    public DocSaleDTO getByIdDocSale(Long id) {
        return docSaleService.getById(id);
    }

    public DocSaleDTO saveDocSale(DocSaleDTO doc) {
        return docSaleService.save(doc);
    }

    // Возвраты
    public List<ReturnHeader> getAllDocReturns() {
        return returnRepository.findAll();
    }

    public ReturnHeader getByIdDocReturn(Long id) {
        return returnRepository.findById(id)
                .orElseThrow(documentNotFound(id));
    }

    public List<ReturnItem> getDocReturnItems(long docId) {
        return returnItemRepository.findAllByDocId(docId);
    }
}
