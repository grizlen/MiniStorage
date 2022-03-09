package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.BuyHeader;
import ru.griz.ms.server.entities.BuyItem;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.BuyItemRepository;
import ru.griz.ms.server.repositories.BuyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final BuyRepository buyRepository;
    private final BuyItemRepository buyItemRepository;

    public List<BuyHeader> getAll() {
        return buyRepository.findAll();
    }

    public BuyHeader getById(Long id) {
        return buyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("document: " + id + " not found.")
        );
    }

    public List<BuyItem> getDocItems(long docId) {
        return buyItemRepository.findAllByDocId(docId);
    }
}
