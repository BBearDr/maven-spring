package com.bbear.example.util.singleton;

/**
 * 饿汉模式，在类加载的时候就直接初始化，这样子虽然能避免线程同步问题，
 * 但是如果不适用，则会在内存中一直存在
 * @author junxiongchen
 * @date 2018/07/23
 */
public class HungryI {
    private HungryI() {
    }

    private final static HungryI singleton = new HungryI();

    public static HungryI getInstance() {
        return singleton;
    }
}
