package ru.griz.msfxclient.presentation.commands;

public class Command {

    public static Builder builder(String cmdName) {
        return new Builder(cmdName);
    }

    public static Builder builder(String cmdName, String title) {
        return new Builder(cmdName).title(title);
    }

    private final String name;
    private String title;
    private CmdAction action;

    private Command(String name) {
        this.name = name;
        title = name;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setAction(CmdAction action) {
        this.action = action;
    }

    public void accept() {
        if (action != null) {
            action.exec();
        }
    }

    public interface CmdAction {
        void exec();

    }

    public static class Builder {

        private final Command command;

        private Builder(String cmdName) {
            command = new Command(cmdName);
        }

        public Builder title(String title) {
            command.title = title;
            return this;
        }

        public Builder action(CmdAction action) {
            command.action = action;
            return this;
        }

        public Command build() {
            return command;
        }
    }
}
