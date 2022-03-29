package model;


import ui.Observer;

import java.util.ArrayList;
import java.util.List;

// abstract class representing what is being observed
public abstract class Subject {
    List<Observer> observerList;

    // EFFECTS: initializing the observerList
    public Subject() {
        observerList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adding observers to the list
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    // EFFECTS: notifying all the observers
    public void notifyAllObserver() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
