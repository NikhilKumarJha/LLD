package com.nikhil.AbstractFactoryPattern;

public class EliteCarFactory {
    private EliteCar eliteCar;

    public EliteCarFactory(EliteCar eliteCar) {
        this.eliteCar = eliteCar;
    }

    public EliteCarFactory() {
    }

    public EliteCar getEliteCar(String type) {
        switch (type) {
            case "mercedes":
                return new Mercedes();
            case "bugatti":
                return new Bugatti();
            default:
                throw new IllegalArgumentException("invalid elite car type");
        }
    }
}