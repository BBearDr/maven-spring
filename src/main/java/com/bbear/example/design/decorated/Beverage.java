package com.bbear.example.design.decorated;

/**
 * 装饰模式基础元素的抽象类
 * 价钱+描述
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
