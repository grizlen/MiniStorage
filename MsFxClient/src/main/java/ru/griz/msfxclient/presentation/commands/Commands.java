package ru.griz.msfxclient.presentation.commands;

import ru.griz.msfxclient.presentation.common.ViewManager;
import ru.griz.msfxclient.presentation.views.DocBuyView;
import ru.griz.msfxclient.buy.JournalBuyView;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    public static String
            CMD_BUY_JOURNAL = "buy_journal",
            CMD_BUY_NEW = "buy_new";

    private static Commands instance;
    private final Map<String, Command> map;

    private static Commands getInstance() {
        return instance == null ? (instance = new Commands()) : instance;
    }

    public static Command get(String cmdName) {
        return getInstance().find(cmdName.toLowerCase());
    }

    // TODO: 07.03.2022 use Builder for Action
    private Commands() {
        map = new HashMap<>();
        create(CMD_BUY_JOURNAL, "Поставки").setAction(() -> ViewManager.currentView(JournalBuyView.class));
        create(CMD_BUY_NEW, "Новая поставка").setAction(() -> ViewManager.currentView(DocBuyView.class));
    }

    private Command create(String cmdName, String title) {
        Command command = Command.builder(cmdName, title).build();
        map.put(command.getName(), command);
        return command;
    }

    private Command find(String cmdName) {
        return map.get(cmdName);
    }
}
