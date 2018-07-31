package com.bbear.example.design.activity_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author junxiongchen
 * @date 2017/11/28
 */
public class DynProxyFactory {
    public static Subject getInstance(){
        Subject subject = new RealSubject();
        InvocationHandler invocationHandler = new SubjectInvocationHandler(subject);
        Subject subject1 = null;
        subject1 = (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), invocationHandler);
        return subject1;
    }
}
