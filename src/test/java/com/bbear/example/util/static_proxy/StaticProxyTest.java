package com.bbear.example.util.static_proxy;

import com.bbear.example.design.static_proxy.StaticSubjectFactory;
import com.bbear.example.design.static_proxy.Subject;

/**
 * 测试静态代理
 *
 * @author junxiongchen
 * @date 2017/11/28
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Subject subject = StaticSubjectFactory.getInstance();
        subject.dealTask("static proxy");
    }
}
