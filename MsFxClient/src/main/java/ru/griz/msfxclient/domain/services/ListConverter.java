package ru.griz.msfxclient.domain.services;

import java.util.List;

public interface ListConverter<T, S> {
    List<T> convert (List<S> source);
}
