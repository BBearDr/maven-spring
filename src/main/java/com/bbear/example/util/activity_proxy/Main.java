package com.bbear.example.util.activity_proxy;

/**
 * @author junxiongchen
 * @date 2018/03/16
 */
public class Main {
    public static void main(String[] args) {
        Subject instance = DynProxyFactory.getInstance();
        instance.dealTask("taskname !");
    }
}
