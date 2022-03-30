package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.Document;
import ru.griz.ms.server.exceptions.ResourceNotFoundException;
import ru.griz.ms.server.repositories.DocumentsRepository;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private static final String DOC_BUY = "BUY";

    private final DocumentsRepository documentsRepository;

    private Supplier<ResourceNotFoundException> documentNotFound(Long id) {
        return () -> new ResourceNotFoundException("Document: " + id + " not found");
    }

    // Все документы
    public List<Document> getAll() {
        return documentsRepository.findAll();
    }

    public Document getById(Long id) {
        return documentsRepository.findById(id)
                .orElseThrow(documentNotFound(id));
    }

    public Document saveBuy(Long id) {
        Document document = new Document();
        document.setId(id);
        document.setType(DOC_BUY);
        return documentsRepository.save(document);
    }
}
