package com.nikhil.ObserverDesignPattern.Observable;

import com.nikhil.ObserverDesignPattern.Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class FootballStockObservable implements StockObservable {

    private List<NotificationAlertObserver> notificationAlertObservers = new ArrayList<>();
    private int stockCount = 0;

    @Override
    public void add(NotificationAlertObserver notificationAlertObserver) {
        notificationAlertObservers.add(notificationAlertObserver);
    }

    @Override
    public void remove(NotificationAlertObserver notificationAlertObserver) {
        notificationAlertObservers.remove(notificationAlertObserver);
    }

    @Override
    public void notifyObservers(int stockCount) {
        for (NotificationAlertObserver notificationAlertObserver : notificationAlertObservers) {
            notificationAlertObserver.update(stockCount);
        }
    }

    public void setStockCount(int addedStock) {
        if (this.stockCount == 0) {
            notifyObservers(addedStock);
        }
        this.stockCount += addedStock;
    }

    public int getStockCount() {
        return stockCount;
    }
}
