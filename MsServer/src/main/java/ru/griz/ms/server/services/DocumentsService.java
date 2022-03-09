package ru.griz.ms.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.griz.ms.server.entities.Document;
import ru.griz.ms.server.repositories.DocumentsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {

    private final DocumentsRepository documentsRepository;

    public List<Document> getAll() {
        return documentsRepository.findAll();
    }
}
