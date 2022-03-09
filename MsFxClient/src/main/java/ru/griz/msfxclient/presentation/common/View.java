package ru.griz.msfxclient.presentation.common;

import javafx.scene.Parent;

public interface View {
    String title();
    Parent getContainer();
    void open();
}
