package com.bbear.example.design.decorated;

/**
 * @author junxiongchen
 * @date 2018/02/01
 */
public abstract class Beverage {
    public String description = "UnKnown Beverage";

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}
