package com.bbear.example.design.static_proxy;

/**
 * 委托类，具体业务的处理
 *
 * @author junxiongchen
 * @date 2017/11/28
 */
public class RealSubject implements Subject {

    @Override
    public void dealTask(String taskName) {
        System.out.println("开始处理任务。。。。。。"+taskName);
        try {
            //代表开始处理业务
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
