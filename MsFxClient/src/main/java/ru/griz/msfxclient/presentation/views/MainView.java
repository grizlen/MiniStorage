package ru.griz.msfxclient.presentation.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.springframework.context.ApplicationContext;
import ru.griz.msfxclient.presentation.commands.Commands;

import java.util.ArrayList;
import java.util.List;

public class MainView extends VBox {

    private static MainView instance;

    public static MainView instance() {
        return instance == null ? (instance = new MainView()) : instance;
    }

    public static<T extends ContentView> T currentView(Class<T> tClass) {
        instance().container.getChildren().clear();
        T view = instance.createView(tClass);
        if (view != null) {
            instance.container.getChildren().add(view);
            instance.setTitle(view.title());
            instance.setCommands(view.commands());
            view.open();
        }
        return view;
    }

    private final NavPanel navPanel;
    private final VBox container;

    private MainView() {
        navPanel = new NavPanel();
        container = new VBox();
        VBox.setVgrow(container, Priority.ALWAYS);
        getChildren().addAll(navPanel, container);
    }

    private void setTitle(String title) {
        navPanel.title(title);
    }

    private void setCommands(Commands.Command... commands) {
        navPanel.commands(commands);
    }

    private <T extends ContentView> T createView(Class<T> tClass) {

        try {
            return tClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class NavPanel extends HBox {
        private final Label lblTitle;
        private final HBox commandsBox;

        public NavPanel() {
            BorderStroke borderStroke = new BorderStroke(
                    Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(1)
            );
            Border border = new Border(borderStroke);
            setBorder(border);
            setPadding(new Insets(4));
            setSpacing(8);
            HBox.setHgrow(this, Priority.ALWAYS);

            lblTitle = new Label();
            commandsBox = new HBox();
            commandsBox.setAlignment(Pos.CENTER_RIGHT);
            commandsBox.setSpacing(4);
            HBox.setHgrow(commandsBox, Priority.ALWAYS);
            MenuButton btnMenu = new MenuButton("...");
            getChildren().addAll(lblTitle, commandsBox, btnMenu);
            btnMenu.getItems().addAll(initMenu());
        }

        private List<MenuItem> initMenu() {
            List<MenuItem> result = new ArrayList<>();
            result.add(Commands.CMD_JOURNAL_BUY.asMenuItem());
            return result;
        }

        public void title(String title) {
            lblTitle.setText(title);
        }

        public void commands(Commands.Command... commands) {
            commandsBox.getChildren().clear();
            for (Commands.Command cmd: commands) {
                commandsBox.getChildren().add(cmd.asButton());
            }
        }
    }
}
