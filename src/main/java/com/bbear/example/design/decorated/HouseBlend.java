package com.bbear.example.design.decorated;

/**
 * 混合咖啡 -- 饮料 相当于基础类
 * @author junxiongchen
 * @date 2018/02/01
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 1.2;
    }
}
