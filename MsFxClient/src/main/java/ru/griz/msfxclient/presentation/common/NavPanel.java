package ru.griz.msfxclient.presentation.common;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ru.griz.msfxclient.presentation.commands.Command;
import ru.griz.msfxclient.presentation.commands.Commands;

public class NavPanel extends HBox {

    private final Label lblTitle;
    private final HBox commandsBox;
    private final MenuButton btnMenu;

    NavPanel() {
        BorderStroke borderStroke = new BorderStroke(
                Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(1)
        );
        Border border = new Border(borderStroke);
        setBorder(border);
        setPadding(new Insets(4));
        setSpacing(4);

        HBox leftPart = new HBox();
        lblTitle = new Label();
        leftPart.getChildren().add(lblTitle);

        commandsBox = new HBox();
        HBox.setHgrow(commandsBox, Priority.ALWAYS);
        commandsBox.setSpacing(4);
        commandsBox.setAlignment(Pos.CENTER_RIGHT);

        btnMenu = new MenuButton("...");
        menuInit(Commands.get(Commands.CMD_BUY_JOURNAL));
        getChildren().addAll(leftPart, commandsBox, btnMenu);
    }

    private void menuInit(Command ... commands) {
        MenuItem item;
        for (Command cmd : commands) {
            item = new MenuItem(cmd.getTitle());
            item.setOnAction(event -> cmd.accept());
            btnMenu.getItems().add(item);
        }
    }

    public void setTitle(String text) {
        lblTitle.setText(text);
    }

    public void commands(Command ... commands) {
        commandsBox.getChildren().clear();
        Button button;
        for (Command cmd : commands) {
            button = new Button(cmd.getTitle());
            button.setOnAction(event -> cmd.accept());
            commandsBox.getChildren().add(button);
        }
    }

}
