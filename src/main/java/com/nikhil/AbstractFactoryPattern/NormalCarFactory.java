package com.nikhil.AbstractFactoryPattern;


public class NormalCarFactory {

    private NormalCar normalVehicle;

    public NormalCarFactory(NormalCar normalVehicle) {
        this.normalVehicle = normalVehicle;
    }

    public NormalCarFactory(){
    }

    public NormalCar getNormalVehicle(String type) {
        switch (type) {
            case "tata":
                return new Tata();
            case "scorpio":
                return new Scorpio();
            default:
                throw new IllegalArgumentException("Invalid normal vehicle type");
        }
    }
}
