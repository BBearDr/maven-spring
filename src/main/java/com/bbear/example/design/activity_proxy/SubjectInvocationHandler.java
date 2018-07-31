package com.bbear.example.design.activity_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author junxiongchen
 * @date 2017/11/28
 */
public class SubjectInvocationHandler implements InvocationHandler {
    //代理类持有委托类一个对象
    private Object object;

    public SubjectInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long time = System.currentTimeMillis();
        //利用反射机制将请求分配给委托类处理
        method.invoke(object, args);
        long time1 = System.currentTimeMillis();
        System.out.println("动态代理执行时间："+(time1 - time));
        return null;
    }
}
