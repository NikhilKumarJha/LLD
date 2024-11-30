package com.nikhil.StrategyDesignPattern.Strategy;

public class SportDriveStrategy implements DriveStrategy {

    @Override
    public void drive() {
        System.out.println("Sports drive strategy");
    }
}
