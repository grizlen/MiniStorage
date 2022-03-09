package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.griz.ms.server.entities.BuyHeader;
import ru.griz.ms.server.entities.BuyItem;
import ru.griz.ms.server.services.BuyService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @GetMapping
    public List<BuyHeader> getAll() {
        return buyService.getAll();
    }

    @GetMapping("/{id}")
    public BuyHeader getById(@PathVariable Long id) {
        return buyService.getById(id);
    }

    @GetMapping("/items/{docId}")
    public List<BuyItem> getDocItems(@PathVariable long docId) {
        return buyService.getDocItems(docId);
    }
}
