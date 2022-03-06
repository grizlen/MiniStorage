package ru.griz.msfxclient.presentation.common;

import javafx.scene.Parent;
import ru.griz.msfxclient.presentation.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ViewManager {

    private static ViewManager instance;

    public static void init() {
        instance = new ViewManager();
    }

    public static Parent mainView() {
        return instance.mainView;
    }

    public static <T extends View> T currentView(Class<T> viewClass) {
        T view = instance.getView(viewClass);
        if (view == null) {
            view = instance.createView(viewClass);
        }
        instance.mainView.setContent(view);
        return view;
    }

    // TODO: 07.03.2022 Что то тут не так
    public static void setNavTitle(String text) {
        instance.mainView.getNavPanel().setTitle(text);
    }

    // TODO: 07.03.2022 Что то тут не так
    public static void setNavCommands(Command... commands) {
        instance.mainView.getNavPanel().commands(commands);
    }

    private final MainView mainView;
    private final Map<String, View> views;

    public ViewManager() {
        mainView = new MainView();
        views = new HashMap<>();
    }

    private <T extends View> T getView(Class<T> viewClass) {
        return (T) views.get(viewClass.getName());
    }

    // TODO: 07.03.2022 ExceptionHandler
    private <T extends View> T createView(Class<T> viewClass) {
        T view;
        try {
            view = viewClass.getDeclaredConstructor().newInstance();
            views.put(viewClass.getName(), view);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            view = null;
            e.printStackTrace();
        }
        return view;
    }
}
