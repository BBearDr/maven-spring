package com.bbear.example.design.observer;

/**
 * 观察者模式，自定义主题接口和观察者
 *
 * @author junxiongchen
 * @date 2018/01/30
 */
public interface Subject {
    /**
     * 添加一个观察者
     */
    public boolean addObserver(Observer observer);

    /**
     * 删除观察者
     * @param observer
     * @return
     */
    public boolean deleteObserver(Observer observer);

    /**
     * 通知
     */
    public boolean notifyObserver();

}
