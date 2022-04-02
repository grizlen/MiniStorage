package ru.griz.ms.server.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DocSaleDTO {
    private Long id;
    private Date date;
    private DepartmentDTO department;
    private List<SaleItemDTO> items;

    private DocSaleDTO() {
        items = new ArrayList<>();
    }

    public static DocSaleDtoBuilder builder() {
        return new DocSaleDtoBuilder();
    }

    public static SaleItemDtoBuilder itemBuilder() {
        return new SaleItemDtoBuilder();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SaleItemDTO {
        Long productId;
        String productName;
        Integer count;
    }

    public static class DocSaleDtoBuilder {

        private final DocSaleDTO dto;

        private DocSaleDtoBuilder() {
            dto = new DocSaleDTO();
        }

        public DocSaleDtoBuilder id(Long id) {
            dto.id = id;
            return this;
        }

        public DocSaleDtoBuilder date(Date date) {
            dto.date = date;
            return this;
        }

        public DocSaleDtoBuilder department(DepartmentDTO department) {
            dto.department = department;
            return this;
        }

        public DocSaleDtoBuilder items(List<SaleItemDTO> items) {
            dto.items.addAll(items);
            return this;
        }

        public DocSaleDTO build() {
            return dto;
        }
    }

    public static class SaleItemDtoBuilder {

        private final SaleItemDTO dto;

        private SaleItemDtoBuilder() {
            dto = new SaleItemDTO();
        }

        public SaleItemDtoBuilder productId(Long productId) {
            dto.productId = productId;
            return this;
        }

        public SaleItemDtoBuilder productName(String productName) {
            dto.productName = productName;
            return this;
        }

        public SaleItemDtoBuilder count(int count) {
            dto.count = count;
            return this;
        }

        public SaleItemDTO build() {
            return dto;
        }
    }
}
