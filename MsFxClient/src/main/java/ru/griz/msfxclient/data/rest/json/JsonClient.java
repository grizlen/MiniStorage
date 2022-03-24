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
        System.out.println("REST GET: " + path);
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
        System.out.println("Response\n" + result);
        return result;
    }

    public String post(String path, String request) {
        System.out.println("REST POST: " + path);
        System.out.println("Request\n" + request);
        String result;
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(rootPath + path))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .setHeader("Content-Type", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            result = httpResponse.body();
        } catch (Exception e) {
            result = e.getClass() + ": " + e.getMessage();
            System.out.println(result);
        }
        System.out.println("Response\n" + result);
        return result;
    }
}
