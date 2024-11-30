package com.nikhil.StrategyDesignPattern.Strategy;

public class TruckDriveStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Truck drive strategy");
    }
}
