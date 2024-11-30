package com.nikhil.StrategyDesignPattern;

import com.nikhil.StrategyDesignPattern.Strategy.SportDriveStrategy;

public class SportsVehicle extends Vehicle{

    SportsVehicle() {
        super(new SportDriveStrategy());
    }


}
