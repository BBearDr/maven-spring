package com.bbear.example.design.observer_java;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式，使用java内置的观察接口
 * @author junxiongchen
 * @date 2018/01/30
 */
public class TeacherSubject extends Observable {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public void setWork(String info){
        //这是pull
//        this.info = info;
        System.out.println("今天的作业是");
        setChanged();
        /**
         * info参数无代表由主题直接推送消息个观察者，有代表由观察者直接拉取主题的信息
         * 参数是在观察者的update中的Object中体现的
         */

        notifyObservers(info);
    }
}
