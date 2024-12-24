package com.nikhil.AbstractFactoryPattern;

public class CarFactory implements AbstractFactory {
    private EliteCarFactory eliteCarFactory;
    private NormalCarFactory normalCarFactory;

    public CarFactory(EliteCarFactory eliteCarFactory, NormalCarFactory normalCarFactory) {
        this.eliteCarFactory = eliteCarFactory;
        this.normalCarFactory = normalCarFactory;
    }

    public CarFactory(EliteCarFactory eliteCarFactory) {
        this.eliteCarFactory = eliteCarFactory;
    }

    public CarFactory(NormalCarFactory normalCarFactory) {
        this.normalCarFactory = normalCarFactory;
    }

    public CarFactory() {
    }

    @Override
    public NormalCarFactory getNormalCarFactory() {
        return this.normalCarFactory;
    }

    @Override
    public EliteCarFactory getEliteCarFactory() {
        return this.eliteCarFactory;
    }
}
