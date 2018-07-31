package com.bbear.example.util.activity_proxy;

import com.bbear.example.design.activity_proxy.DynProxyFactory;
import com.bbear.example.design.activity_proxy.Subject;

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
