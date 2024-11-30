package com.nikhil.ObserverDesignPattern.Observer;

public class EmailAlertObserver implements NotificationAlertObserver {
    @Override
    public void update(int stockCount) {
        System.out.println("Email: Football stocks: " + stockCount);
    }
}
