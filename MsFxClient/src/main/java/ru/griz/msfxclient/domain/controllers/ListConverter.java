package ru.griz.msfxclient.domain.controllers;

import java.util.List;

public interface ListConverter<T, S> {
    List<T> convert (List<S> source);
}
