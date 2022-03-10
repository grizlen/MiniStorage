package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griz.ms.server.entities.SaleHeader;
import ru.griz.ms.server.entities.SaleItem;
import ru.griz.ms.server.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public List<SaleHeader> getAll() {
        return saleService.getAll();
    }

    @GetMapping("/{id}")
    public SaleHeader getById(@PathVariable Long id) {
        return saleService.getById(id);
    }

    @GetMapping("/items/{docId}")
    public List<SaleItem> getDocItems(@PathVariable long docId) {
        return saleService.getDocItems(docId);
    }
}
