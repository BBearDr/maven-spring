package com.bbear.example.util.observer;

import com.sun.org.apache.xpath.internal.axes.OneStepIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/01/30
 */
public class TeacherSubject implements Subject {
    private List<Observer> list = new ArrayList<Observer>();
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Observer> getList() {
        return list;
    }

    public void setList(List<Observer> list) {
        this.list = list;
    }

    @Override
    public boolean addObserver(Observer observer) {
        list.add(observer);
        if (list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteObserver(Observer observer) {
        boolean remove = list.remove(observer);
        return remove;
    }

    @Override
    public boolean notifyObserver() {
        if (list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update();
        }
        return true;
    }
    public void setWork(){
        System.out.println("今天作业");
        this.notifyObserver();
    }
}
