package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String formatDate(Date date) {
        return formatter.format(date);
    }

    private final DocumentsRepository documentsRepository;
    private final BuyRepository buyRepository;
    private final BuyItemRepository buyItemRepository;
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ReturnRepository returnRepository;
    private final ReturnItemRepository returnItemRepository;
    private final ProductService productService;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    // Все документы
    public List<Document> getAllDocs() {
        return documentsRepository.findAll();
    }

    public Document getByIdDoc(Long id) {
        return documentsRepository.findById(id)
                .orElseThrow(documentNotFound(id));
    }

    // Поступления
    public List<BuyHeader> getAllDocBuys() {
        return buyRepository.findAll();
    }

    public DocBuyDTO getByIdDocBuy(Long id) {
        BuyHeader header = buyRepository.findById(id)
                .orElseThrow(documentNotFound(id));
        List<BuyItem> items = getDocBuyItems(header.getId());
        DocBuyDTO result = new DocBuyDTO();
        result.setId(header.getId());
        result.setDate(formatDate(header.getDate()));
        items.forEach(i -> {
            DocBuyDTO.BuyItemDTO item = new DocBuyDTO.BuyItemDTO();
            item.setId(i.getId());
            item.setProductId(i.getProductId());
            item.setProductName(productService.getById(i.getProductId()).getName());
            item.setCount(i.getCount());
            result.getItems().add(item);
        });
        return result;
    }

    public List<BuyItem> getDocBuyItems(long docId) {
        return buyItemRepository.findAllByDocId(docId);
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
