package ru.griz.msfxclient.data.repositories;

import ru.griz.msfxclient.data.entities.BuyHeader;
import ru.griz.msfxclient.data.sqlite.SqLiteDbRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyHeaderRepository extends SqLiteDbRepository<BuyHeader> {
    private final List<BuyHeader> data;

    public BuyHeaderRepository() {
        data = new ArrayList<BuyHeader>();
    }

    @Override
    public List<BuyHeader> findAll() {
        ArrayList<BuyHeader> result = new ArrayList<>();
        result.addAll(data);
        return result;
    }

    @Override
    public void save(BuyHeader model) {

    }
}
