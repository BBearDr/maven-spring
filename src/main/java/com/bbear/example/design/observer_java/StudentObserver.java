package com.bbear.example.design.observer_java;

import java.util.Observable;
import java.util.Observer;

/**
 * @author junxiongchen
 * @date 2018/01/30
 */
public class StudentObserver implements Observer {
    private Observable ob;

    public StudentObserver(Observable ob) {
        this.ob = ob;
        ob.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        TeacherSubject t = (TeacherSubject) o;
        System.out.println("studentObserver update:" + arg.toString());
    }
}
