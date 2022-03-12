package ru.griz.msfxclient.domain.services;

public interface Converter<T, S> {
    T convert (S source);
}
