package ru.griz.msfxclient.data.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.griz.msfxclient.domain.documents.buy.DocBuy;

import java.util.Collections;
import java.util.List;

@Component
public class RestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_ROOT = "http://https://my-simplecrm.herokuapp.com/api/";

    public List<DocBuy> getAllDocBuys() {
        DocBuy[] docBuys = restTemplate.getForObject(API_ROOT + "buy/", DocBuy[].class);
        return docBuys != null ? List.of(docBuys) : Collections.emptyList();
    }
}
