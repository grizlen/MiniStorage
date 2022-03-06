module ru.griz.msfxclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens ru.griz.msfxclient to javafx.fxml;
    opens ru.griz.msfxclient.data.entities to com.google.gson;
    exports ru.griz.msfxclient;
}