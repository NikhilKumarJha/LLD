package com.nikhil.DecoratorDesignPattern.Decorator;

import com.nikhil.DecoratorDesignPattern.BasePizza;

public class Mushroom implements Topping{

    private final BasePizza basePizza;

    public Mushroom(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost()+20;
    }
}
