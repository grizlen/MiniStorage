package ru.griz.msfxclient.domain.controllers;

public interface Converter<T, S> {
    T convert (S source);
}
