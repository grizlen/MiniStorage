package ru.griz.ms.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DocBuyDTO {
    private Long id;
    private Date date;
    private List<BuyItemDTO> items;

    private DocBuyDTO() {
        items = new ArrayList<>();
    }

    public static DocBuyDtoBuilder builder() {
        return new DocBuyDtoBuilder();
    }

    public static BuyItemDtoBuilder itemBuilder() {
        return new BuyItemDtoBuilder();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BuyItemDTO {
        Long productId;
        String productName;
        Integer count;
    }

    public static class DocBuyDtoBuilder {

        private final DocBuyDTO dto;

        private DocBuyDtoBuilder() {
            dto = new DocBuyDTO();
        }

        public DocBuyDtoBuilder id(Long id) {
            dto.id = id;
            return this;
        }

        public DocBuyDtoBuilder date(Date date) {
            dto.date = date;
            return this;
        }

        public DocBuyDtoBuilder items(List<BuyItemDTO> items) {
            dto.items.addAll(items);
            return this;
        }

        public DocBuyDTO build() {
            return dto;
        }
    }

    public static class BuyItemDtoBuilder {

        private final BuyItemDTO dto;

        private BuyItemDtoBuilder() {
            dto = new BuyItemDTO();
        }

        public BuyItemDtoBuilder productId(Long productId) {
            dto.productId = productId;
            return this;
        }

        public BuyItemDtoBuilder productName(String productName) {
            dto.productName = productName;
            return this;
        }

        public BuyItemDtoBuilder count(int count) {
            dto.count = count;
            return this;
        }

        public BuyItemDTO build() {
            return dto;
        }
    }
}
