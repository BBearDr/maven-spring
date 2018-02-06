package com.bbear.example.util.static_proxy;

import javax.sound.midi.Soundbank;

/**
 * 代理类，将任务分配给委托类执行，记录时间
 *
 * @author junxiongchen
 * @date 2017/11/28
 */
public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }
    @Override
    public void dealTask(String taskName) {
        long time = System.currentTimeMillis();
        subject.dealTask(taskName);
        long time1 = System.currentTimeMillis();
        System.out.println("总共耗时："+(time1 - time));

    }
}
