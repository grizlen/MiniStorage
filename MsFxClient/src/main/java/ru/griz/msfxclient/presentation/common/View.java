package ru.griz.msfxclient.presentation.common;

import javafx.scene.Parent;
import ru.griz.msfxclient.presentation.commands.Command;

public interface View {
    String title();
    Command[] navCommands();
    Parent container();
    void open();
}
