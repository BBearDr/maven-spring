package com.bbear.example.util.static_proxy;

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
