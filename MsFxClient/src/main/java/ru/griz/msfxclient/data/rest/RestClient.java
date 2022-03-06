package ru.griz.msfxclient.data.rest;

import ru.griz.msfxclient.data.rest.json.JsonClient;
import ru.griz.msfxclient.data.rest.json.JsonMapper;

import java.util.List;

public class RestClient {

    private static RestClient self;

    private final JsonClient client;
    private final JsonMapper mapper;

    private RestClient() {
        client = new JsonClient("http://localhost/api");
        mapper = new JsonMapper();
    }

    public static RestClient instance() {
        if (self == null) {
            self = new RestClient();
        }
        return self;
    }

    public <T> T get(String path, Class<T> tClass) {
        String response = client.get(path);
        System.out.println(response);
        return mapper.fromJson(response, tClass);
    }

    public <T> T[] getArray(String path, Class<T[]> tClass) {
        String response = client.get(path);
        System.out.println(response);
        return mapper.arrayFromJson(response, tClass);
    }

    public <T> List<T> getList(String path, Class<T> itemClass) {
        String response = client.get(path);
        System.out.println(response);
        return mapper.listFromJson(response, itemClass);
    }
}
