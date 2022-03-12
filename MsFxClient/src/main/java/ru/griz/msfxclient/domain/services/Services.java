package ru.griz.msfxclient.domain.services;

import java.util.HashMap;
import java.util.Map;

public class Services {

    private static Services self;

    private static Services self() {
        return self == null ? self = new Services() : self;
    }

    public static <T> T get(Class<T> tClass) {
        T result = self().find(tClass.getName());
        return result == null ? self.create(tClass): result;
    }

    private final Map<String, Object> map = new HashMap<>();

    private <T> T find(String name) {
        return (T) map.get(name);
    }

    private <T> T create(Class<T> tClass) {
        T result = null;
        try {
            result = tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            map.put(tClass.getName(), result);
        }
        return result;
    }
}
