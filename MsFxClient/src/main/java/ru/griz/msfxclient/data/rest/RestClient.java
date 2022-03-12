package ru.griz.msfxclient.data.rest;

import ru.griz.msfxclient.data.rest.json.JsonClient;
import ru.griz.msfxclient.data.rest.json.JsonMapper;

import java.util.List;

public class RestClient {

    private final JsonClient client;
    private final JsonMapper mapper;

    RestClient(String clientPath) {
        client = new JsonClient(clientPath);
        mapper = new JsonMapper();
    }

    protected <T> T get(String path, Class<T> tClass) {
        String response = client.get(path);
        return mapper.fromJson(response, tClass);
    }

    protected <T> List<T> getList(String path, Class<T> itemClass) {
        String response = client.get(path);
        return mapper.listFromJson(response, itemClass);
    }
}
