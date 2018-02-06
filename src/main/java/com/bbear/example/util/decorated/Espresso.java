package com.bbear.example.util.decorated;

/**
 * 浓咖啡 --饮料 相当于基础类
 * @author junxiongchen
 * @date 2018/02/01
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 2.1;
    }
}
