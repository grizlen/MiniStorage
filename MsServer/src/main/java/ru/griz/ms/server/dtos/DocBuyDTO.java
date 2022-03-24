package ru.griz.ms.server.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DocBuyDTO {
    private Long id;
    private String date;
    private List<BuyItemDTO> items;

    public DocBuyDTO() {
        items = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class BuyItemDTO {
        Long id;
        Long productId;
        String productName;
        Integer count;
    }
}
