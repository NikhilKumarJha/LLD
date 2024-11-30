package com.nikhil.StrategyDesignPattern;

public class Main {
    public static void main(String[] args) {
        Vehicle normalVehicle = new NormalVehicle();
        normalVehicle.drive();

        Vehicle sportsVehicle = new SportsVehicle();
        sportsVehicle.drive();

        Vehicle truckVehicle = new TruckVehicle();
        truckVehicle.drive();
    }
}