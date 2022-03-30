package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.entities.BuyHeader;
import ru.griz.ms.server.entities.BuyItem;
import ru.griz.ms.server.entities.Document;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.BuyItemRepository;
import ru.griz.ms.server.repositories.BuyRepository;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocBuyService {

    private final BuyRepository buyRepository;
    private final BuyItemRepository buyItemRepository;
    private final DocumentService documentService;
    private final ProductService productService;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    public List<BuyHeader> getAll() {
        return buyRepository.findAll();
    }

    public DocBuyDTO getById(Long id) {
        BuyHeader header = buyRepository.findById(id)
                .orElseThrow(documentNotFound(id));
        return DocBuyDTO.builder()
                .id(header.getId())
                .date(header.getDate())
                .items(
                        getItems(header.getId()).stream()
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

        Document document = documentService.saveBuy(doc.getId());

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

        return getById(docId);
    }

    private List<BuyItem> getItems(long docId) {
        return buyItemRepository.findAllByDocId(docId);
    }

}
