package ru.griz.msfxclient.presentation.common;

import javafx.scene.Parent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ViewManager {

    private static ViewManager self;

    private static ViewManager self() {
        return self == null ? (self = new ViewManager()) : self;
    }

    public static Parent mainView() {
        return self().mainView;
    }

    public static <T extends View> T currentView(Class<T> viewClass) {
        T view = self().findView(viewClass);
        if (view == null) {
            view = self.createView(viewClass);
        }
        self.mainView.setContent(view);
        return view;
    }

    private final MainView mainView;
    private final Map<String, View> views;

    private ViewManager() {
        mainView = new MainView();
        views = new HashMap<>();
    }

    private <T extends View> T findView(Class<T> viewClass) {
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
