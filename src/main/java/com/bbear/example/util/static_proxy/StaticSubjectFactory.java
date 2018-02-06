package com.bbear.example.util.static_proxy;

/**
 * 静态代理工厂类，获得代理对象
 * 使用时客户端不知道是委托类还是代理类
 *
 * @author junxiongchen
 * @date 2017/11/28
 */
public class StaticSubjectFactory {
    public static Subject getInstance(){
        return new ProxySubject(new RealSubject());
    }
}
