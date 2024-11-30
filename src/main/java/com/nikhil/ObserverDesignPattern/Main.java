package com.nikhil.ObserverDesignPattern;

import com.nikhil.ObserverDesignPattern.Observable.FootballStockObservable;
import com.nikhil.ObserverDesignPattern.Observable.StockObservable;
import com.nikhil.ObserverDesignPattern.Observer.EmailAlertObserver;
import com.nikhil.ObserverDesignPattern.Observer.MessageAlertObserver;
import com.nikhil.ObserverDesignPattern.Observer.NotificationAlertObserver;

public class Main {
    public static void main(String[] args) {
        NotificationAlertObserver messageObserver = new MessageAlertObserver();
        NotificationAlertObserver emailObserver = new EmailAlertObserver();
        StockObservable stockObservable = new FootballStockObservable();
        stockObservable.add(messageObserver);
        stockObservable.add(emailObserver);
        stockObservable.setStockCount(10);
    }
}