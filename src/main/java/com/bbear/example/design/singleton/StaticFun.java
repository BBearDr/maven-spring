package com.bbear.example.design.singleton;

/**
 * 静态内部类实例化，在类被装载的时候并不会进行实例化，
 * 只有在getInstance()方法被调用的时候会加载（内部类使用的时候加载，不适用不加载）
 * 又因为是静态内部类，所以保证初始化实例的时候只会加载一个
 * @author junxiongchen
 * @date 2018/07/23
 */
public class StaticFun {
    private StaticFun() {
    }
    private static class Singleton{
        private final static StaticFun singleton = new StaticFun();
    }
    public static StaticFun getIntance() {
        return Singleton.singleton;
    }
}
