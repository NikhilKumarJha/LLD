package com.nikhil.ObserverDesignPattern.Observer;

public class MessageAlertObserver implements NotificationAlertObserver {
    @Override
    public void update(int stockCount) {
        System.out.println("Message: Football stock updated: " + stockCount);
    }
}
