package ru.griz.msfxclient.data.cache.db;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FieldValues {

    private Map<String, String> map = new HashMap<>();

    public void put(String field, Long value) {
        String v = (value == null) ? "NULL" : String.valueOf(value);
        map.put(field, v);
    }

    public void put(String field, String value) {
        String v = (value == null) ? "NULL" : "'" + value + "'";
        map.put(field, v);
    }

    public String fields() {
        return map.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    public String values() {
        return map.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.joining(", "));
    }

    public String fieldsAndValues() {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
