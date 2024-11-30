package com.nikhil.ObserverDesignPattern.Observable;

import com.nikhil.ObserverDesignPattern.Observer.NotificationAlertObserver;

public interface StockObservable {

    void add(NotificationAlertObserver notificationAlertObserver);

    void remove(NotificationAlertObserver notificationAlertObserver);

    void notifyObservers(int stockCount);

    void setStockCount(int addedStock);
}
