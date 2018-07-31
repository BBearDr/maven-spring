package com.bbear.example.design.activity_proxy;

/**
 * @author junxiongchen
 * @date 2017/11/28
 *
 * 代理接口，处理给定名字的任务
 */
public interface Subject {
    /**
     * 执行任务
     * @param taskName 任务名称
     */
    public void dealTask(String taskName);
}
