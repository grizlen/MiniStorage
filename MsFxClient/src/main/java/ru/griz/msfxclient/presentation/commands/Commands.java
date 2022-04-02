package ru.griz.msfxclient.presentation.commands;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import ru.griz.msfxclient.presentation.views.MainView;
import ru.griz.msfxclient.domain.documents.buy.JournalBuyView;

public class Commands {
    public static final Command CMD_JOURNAL_BUY = new Command("Поступления", () ->
            MainView.currentView(JournalBuyView.class));

    public static Command create(String name, CommandAction action) {
        return new Command(name, action);
    }

    public static class Command {
        private final String name;
        private final CommandAction action;

        private Command(String name, CommandAction action) {
            this.name = name;
            this.action = action;
        }

        public MenuItem asMenuItem() {
            MenuItem result = new MenuItem(name);
            result.setOnAction(actionEvent -> exec());
            return result;
        }

        public Button asButton() {
            Button result = new Button(name);
            result.setOnAction(actionEvent -> exec());
            return result;
        }

        public void exec() {
            System.out.println("Command: " + name);
            if (action != null) {
                action.exec();
            }
        }
    }

    public interface CommandAction {
        void exec();
    }
}
