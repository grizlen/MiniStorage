package ru.griz.msfxclient.domain.documents.buy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.griz.msfxclient.data.rest.RestClient;

import java.util.List;

@Service
public class DocBuyService {

    @Autowired
    private RestClient restClient;

    public List<DocBuy> getAll() {
        return restClient.getAllDocBuys();
    }
}
