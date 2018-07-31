package com.bbear.example.design.decorated;

/**
 * 装饰者模式：不变的作为基础抽象类，变得继承不变的作为抽象装饰者类，
 * 所有的装饰者继承抽象装饰者----组合的叠加,例如JAVA/IO
 * 但是问题是：组合的缺点就是小类太多，容易出错
 * @author junxiongchen
 * @date 2018/02/01
 */
public class DecoratedMain {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println("description:"+beverage.getDescription() + ",cost:" + beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mike(beverage1);
        beverage1 = new Mocha(beverage1);
        System.out.println("description:"+beverage1.getDescription() + ",cost:" + beverage1.cost());
        String a = "";
    }
}
