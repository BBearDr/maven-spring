package com.bbear.example.design.order;

/**
 * 接收命令要实现的具体效果
 * @author junxiongchen
 * @date 2018/02/05
 */
public class ConcreateReceiver extends Receiver {

    @Override
    public void doSomething() {
        System.out.println("doSomething "+ this.getClass().getName());
    }
}
