package com.bbear.example.util.activity_proxy;

/**
 * @author junxiongchen
 * @date 2017/11/28
 */
public class DynProxyFactoryTest {
    public static void main(String[] args) {
        Subject subject = DynProxyFactory.getInstance();
        subject.dealTask("activity_proxy");
    }
}
