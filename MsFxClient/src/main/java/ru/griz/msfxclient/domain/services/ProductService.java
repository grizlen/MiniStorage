package ru.griz.msfxclient.domain.services;

import ru.griz.msfxclient.data.cache.DbContext;
import ru.griz.msfxclient.data.cache.repositories.ProductsRepository;
import ru.griz.msfxclient.data.entities.ProductEntity;
import ru.griz.msfxclient.domain.models.ProductItem;

import java.util.List;

public class ProductService {

    private final ProductsRepository repository = DbContext.repository(ProductsRepository.class);

    private final Converter<ProductItem, ProductEntity> fromEntity =
            entity -> {
                ProductItem item = new ProductItem();
                item.setId(entity.getId());
                item.setName(entity.getName());
                return item;
            };

    private final ListConverter<ProductItem, ProductEntity> fromListEntity =
            entities -> entities.stream().map(fromEntity::convert).toList();

    public List<ProductItem> getAll() {
        return fromListEntity.convert(repository.findAll());
    }
}
