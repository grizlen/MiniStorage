package ru.griz.msfxclient.data.rest.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
    private final Gson gson;

    public JsonMapper() {
        gson = new Gson();
    }

    public <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    public <T> T[] arrayFromJson(String json, Class<T[]> tClass) {
        return gson.fromJson(json, tClass);
    }

    public <T> List<T> listFromJson(String json, Class<T> itemClass) {
        Type type = TypeToken.getParameterized(ArrayList.class, itemClass).getType();
        return gson.fromJson(json, type);
    }

    public <T> String toJson(T item) {
        return gson.toJson(item);
    }
}
