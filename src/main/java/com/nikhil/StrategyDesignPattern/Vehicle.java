package com.nikhil.StrategyDesignPattern;

import com.nikhil.StrategyDesignPattern.Strategy.DriveStrategy;

public class Vehicle {
    public final DriveStrategy driveStrategy;

    Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    void drive() {
        driveStrategy.drive();
    }
}
