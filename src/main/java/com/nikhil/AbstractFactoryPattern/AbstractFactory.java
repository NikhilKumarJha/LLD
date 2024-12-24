package com.nikhil.AbstractFactoryPattern;

public interface AbstractFactory {
    NormalCarFactory getNormalCarFactory();
    EliteCarFactory getEliteCarFactory();
}
