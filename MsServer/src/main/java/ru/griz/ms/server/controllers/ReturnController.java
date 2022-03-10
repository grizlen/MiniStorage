package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griz.ms.server.entities.ReturnHeader;
import ru.griz.ms.server.entities.ReturnItem;
import ru.griz.ms.server.services.ReturnService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/return")
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @GetMapping
    public List<ReturnHeader> getAll() {
        return returnService.getAll();
    }

    @GetMapping("/{id}")
    public ReturnHeader getById(@PathVariable Long id) {
        return returnService.getById(id);
    }

    @GetMapping("/items/{docId}")
    public List<ReturnItem> getDocItems(@PathVariable long docId) {
        return returnService.getDocItems(docId);
    }
}
