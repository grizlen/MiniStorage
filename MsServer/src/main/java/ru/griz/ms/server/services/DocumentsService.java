package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentsService {

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
        return DocBuyDTO.builder()
                .id(header.getId())
                .date(header.getDate())
                .items(
                        getDocBuyItems(header.getId()).stream()
                                .map(item ->
                                        DocBuyDTO.itemBuilder()
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
    public DocBuyDTO saveDocBuy(DocBuyDTO doc) {
        if (doc.getId() != null) {
            buyItemRepository.deleteByDocId(doc.getId());
        }
        Document document = new Document();
        document.setId(doc.getId());
        document.setType("BUY");
        document = documentsRepository.save(document);

        Long docId = document.getId();

        BuyHeader header = new BuyHeader();
        header.setId(docId);
        header.setDate(doc.getDate());
        buyRepository.save(header);

        List<BuyItem> buyItems = doc.getItems().stream()
                .map(i -> {
                    BuyItem item = new BuyItem();
                    item.setDocId(docId);
                    item.setId(null);
                    item.setProductId(i.getProductId());
                    item.setCount(i.getCount());
                    return item;
                })
                .collect(Collectors.toList());
        buyItemRepository.saveAll(buyItems);

        return getByIdDocBuy(docId);
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
