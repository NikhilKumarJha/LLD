package com.nikhil.FactoryDesignPattern;

public class ShapeFactory {
    private final Shape shape;

    public ShapeFactory(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape(){
        return this.shape;
    }
}
