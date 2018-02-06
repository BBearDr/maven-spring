package com.bbear.example.util.decorated;

/**
 * 装饰者
 * @author junxiongchen
 * @date 2018/02/01
 */
public class Mike extends Condiment {
    private Beverage beverage;

    public Mike(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mike";
    }

    @Override
    public double cost() {
        return beverage.cost() + 3;
    }
}
