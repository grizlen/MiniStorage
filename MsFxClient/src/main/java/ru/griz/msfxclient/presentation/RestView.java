package ru.griz.msfxclient.presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ru.griz.msfxclient.data.entities.ProductEntity;
import ru.griz.msfxclient.data.rest.RestClient;

import java.util.List;
import java.util.stream.Collectors;

public class RestView extends VBox {

    private final Label lblResponse;

    public RestView() {
        Button btnGet = new Button("GET");
        btnGet.setOnAction(this::btnGetOnAction);
        lblResponse = new Label();
        getChildren().addAll(btnGet, lblResponse);
    }

    private void btnGetOnAction(ActionEvent actionEvent) {
        RestClient client = RestClient.instance();
        List<ProductEntity> productEntities = client.getList("/products/", ProductEntity.class);
        String s = productEntities.stream().map(p -> p.toString()).collect(Collectors.joining(",\n", "[", "]"));
        lblResponse.setText(s);

//        Product product = new Product();
//        product.setId(1);
//        product.setName("product 1");
//        Gson gson = new Gson();
//        String json = gson.toJson(product);
//        System.out.println(json);
//        Product product2 = gson.fromJson(json, Product.class);
//        System.out.println(product2.toString());
        actionEvent.consume();
    }
}
