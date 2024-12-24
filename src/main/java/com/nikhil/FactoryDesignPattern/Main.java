package com.nikhil.FactoryDesignPattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory circleShapeFactory = new ShapeFactory(new Circle());
        ShapeFactory rectangleShapeFactory = new ShapeFactory(new Rectangle());
        Shape circle = circleShapeFactory.getShape();
        Shape rectangle = rectangleShapeFactory.getShape();
        circle.draw();
        rectangle.draw();
    }
}
