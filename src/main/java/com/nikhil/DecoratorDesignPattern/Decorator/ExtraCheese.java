package com.nikhil.DecoratorDesignPattern.Decorator;

import com.nikhil.DecoratorDesignPattern.BasePizza;

public class ExtraCheese implements Topping{

    private final BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return this.basePizza.cost()+10;
    }
}
