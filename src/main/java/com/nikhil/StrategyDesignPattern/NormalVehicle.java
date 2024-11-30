package com.nikhil.StrategyDesignPattern;

import com.nikhil.StrategyDesignPattern.Strategy.NormalDriveStrategy;

public class NormalVehicle extends Vehicle {

    NormalVehicle(){
        super(new NormalDriveStrategy());
    }
}
