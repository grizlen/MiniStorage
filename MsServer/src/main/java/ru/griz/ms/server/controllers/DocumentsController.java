package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griz.ms.server.entities.Document;
import ru.griz.ms.server.services.DocumentsService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/documents")
@RequiredArgsConstructor
public class DocumentsController {

    private final DocumentsService documentsService;

    @GetMapping("/")
    public List<Document> getAll() {
        return documentsService.getAll();
    }
}
