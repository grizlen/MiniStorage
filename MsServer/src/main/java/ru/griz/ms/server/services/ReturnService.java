package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.ReturnHeader;
import ru.griz.ms.server.entities.ReturnItem;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.ReturnItemRepository;
import ru.griz.ms.server.repositories.ReturnRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService {
    private final ReturnRepository returnRepository;
    private final ReturnItemRepository returnItemRepository;

    public List<ReturnHeader> getAll() {
        return returnRepository.findAll();
    }

    public ReturnHeader getById(Long id) {
        return returnRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("document: " + id + " not found.")
        );
    }

    public List<ReturnItem> getDocItems(long docId) {
        return returnItemRepository.findAllByDocId(docId);
    }
}
