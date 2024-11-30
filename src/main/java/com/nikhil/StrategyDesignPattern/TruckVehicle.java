package com.nikhil.StrategyDesignPattern;

import com.nikhil.StrategyDesignPattern.Strategy.DriveStrategy;
import com.nikhil.StrategyDesignPattern.Strategy.TruckDriveStrategy;

public class TruckVehicle extends Vehicle {

    TruckVehicle() {
        super(new TruckDriveStrategy());
    }
}
