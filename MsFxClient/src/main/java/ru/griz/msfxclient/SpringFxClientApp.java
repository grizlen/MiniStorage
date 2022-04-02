package ru.griz.msfxclient;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFxClientApp {
    public static void main(String[] args) {
        Application.launch(FxClientApp.class, args);
    }
}
