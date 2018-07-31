package com.bbear.example.design.decorated;

/**
 * 装饰者
 * @author junxiongchen
 * @date 2018/02/01
 */
public class Mocha extends Condiment {
    private Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 2;
    }
}
