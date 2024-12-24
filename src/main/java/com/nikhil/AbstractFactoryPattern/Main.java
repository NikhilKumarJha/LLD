package com.nikhil.AbstractFactoryPattern;

public class Main {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new CarFactory(new EliteCarFactory(), new NormalCarFactory());
        NormalCarFactory normalCarFactory = abstractFactory.getNormalCarFactory();
        EliteCarFactory eliteCarFactory = abstractFactory.getEliteCarFactory();
        NormalCar tata = normalCarFactory.getNormalVehicle("tata");
        tata.drive();
        NormalCar scorpio = normalCarFactory.getNormalVehicle("scorpio");
        scorpio.drive();
        EliteCar mercedes = eliteCarFactory.getEliteCar("mercedes");
        mercedes.drive();
        EliteCar bugatti = eliteCarFactory.getEliteCar("bugatti");
        bugatti.drive();

    }
}
