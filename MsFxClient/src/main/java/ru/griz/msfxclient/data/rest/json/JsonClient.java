package ru.griz.msfxclient.data.rest.json;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonClient {

    private final HttpClient httpClient;
    private final String rootPath;

    public JsonClient(String rootPath) {
        httpClient = HttpClient.newHttpClient();
        this.rootPath = rootPath;
    }

    public String get(String path) {
        String result;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(rootPath + path))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
        } catch (Exception e) {
            result = e.getClass() + ": " + e.getMessage();
            System.out.println(result);
        }
        return result;
    }
}
