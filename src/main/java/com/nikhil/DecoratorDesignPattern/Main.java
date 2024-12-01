package com.nikhil.DecoratorDesignPattern;

import com.nikhil.DecoratorDesignPattern.Decorator.ExtraCheese;
import com.nikhil.DecoratorDesignPattern.Decorator.Mushroom;

public class Main {
    public static void main(String[] args) {
        BasePizza pizza = new FarmHousePizza();
        System.out.println(pizza.cost());
        BasePizza pizzaWithExtraCheese = new ExtraCheese(new FarmHousePizza());
        System.out.println(pizzaWithExtraCheese.cost());
        BasePizza pizzaWithExtraCheeseWithMushroom = new Mushroom(new ExtraCheese(new FarmHousePizza()));
        System.out.println(pizzaWithExtraCheeseWithMushroom.cost());
        BasePizza pizzaWithExtraMushroomWithCheese = new ExtraCheese(new Mushroom(new FarmHousePizza()));
        System.out.println(pizzaWithExtraMushroomWithCheese.cost());
    }
}