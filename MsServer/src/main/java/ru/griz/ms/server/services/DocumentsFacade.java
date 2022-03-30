package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.ReturnItemRepository;
import ru.griz.ms.server.repositories.ReturnRepository;
import ru.griz.ms.server.repositories.SaleItemRepository;
import ru.griz.ms.server.repositories.SaleRepository;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class DocumentsFacade {

    private final DocumentService documentService;
    private final DocBuyService docBuyService;
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
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
    public List<BuyHeader> getAllDocBuys() {
        return docBuyService.getAll();
    }

    public DocBuyDTO getByIdDocBuy(Long id) {
        return docBuyService.getById(id);
    }

    public DocBuyDTO saveDocBuy(DocBuyDTO doc) {
        return docBuyService.saveDocBuy(doc);
    }

    // Отгрузки
    public List<SaleHeader> getAllDocSales() {
        return saleRepository.findAll();
    }

    public SaleHeader getByIdDocSale(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(documentNotFound(id));
    }

    public List<SaleItem> getDocSaleItems(long docId) {
        return saleItemRepository.findAllByDocId(docId);
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
