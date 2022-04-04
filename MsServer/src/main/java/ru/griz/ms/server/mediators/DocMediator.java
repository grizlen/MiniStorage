package ru.griz.ms.server.mediators;

import java.util.List;

public interface DocMediator<T> {
    List<T> getAll();
    T getById(Long id);
    T save(T doc);
}
