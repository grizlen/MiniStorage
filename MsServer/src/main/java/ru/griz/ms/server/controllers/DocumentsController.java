package ru.griz.ms.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.griz.ms.server.dtos.DocBuyDTO;
import ru.griz.ms.server.dtos.DocSaleDTO;
import ru.griz.ms.server.entities.*;
import ru.griz.ms.server.services.DocumentsFacade;

import java.util.List;

@RestController
@RequestMapping(path = "/api/doc")
@RequiredArgsConstructor
public class DocumentsController {

    private final DocumentsFacade documentsFacade;

    // Все документы
    @GetMapping("/")
    public List<Document> getAllDocs() {
        return documentsFacade.getAllDocs();
    }

    @GetMapping("/{id}")
    public Document getByIdDoc(@PathVariable Long id) {
        return documentsFacade.getByIdDoc(id);
    }

    // Поступления
    @GetMapping("/buy/")
    public List<DocBuyDTO> getAllDocBuys() {
        return documentsFacade.getAllDocBuys();
    }

    @GetMapping("/buy/{id}")
    public DocBuyDTO getByIdDocBuy(@PathVariable Long id) {
        return documentsFacade.getByIdDocBuy(id);
    }

    @PostMapping("/buy/")
    public DocBuyDTO postDocBuy(@RequestBody DocBuyDTO doc) {
        return documentsFacade.saveDocBuy(doc);
    }

    // Отгрузки
    @GetMapping("/sale/")
    public List<DocSaleDTO> getAllDocSales() {
        return documentsFacade.getAllDocSales();
    }

    @GetMapping("/sale/{id}")
    public DocSaleDTO getByIdDocSale(@PathVariable Long id) {
        return documentsFacade.getByIdDocSale(id);
    }

    @PostMapping("/sale/")
    public DocSaleDTO postDocSale(@RequestBody DocSaleDTO doc) {
        return documentsFacade.saveDocSale(doc);
    }

    // Возвраты
    @GetMapping("/return/")
    public List<ReturnHeader> getAllDocReturns() {
        return documentsFacade.getAllDocReturns();
    }

    @GetMapping("/return/{id}")
    public ReturnHeader getByIdDocReturn(@PathVariable Long id) {
        return documentsFacade.getByIdDocReturn(id);
    }

    @GetMapping("/return/items/{docId}")
    public List<ReturnItem> getDocReturnItems(@PathVariable long docId) {
        return documentsFacade.getDocReturnItems(docId);
    }

}
