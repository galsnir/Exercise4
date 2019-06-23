package com.game.exercise4;

public interface ObservableI {

    void addToObserver(ObserverI obs);
    void notifyObservers(float x, float y);
}
