package com.bbear.example.util.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/01/30
 */
public class Main {
    public static void main(String[] args) {
        List<Observer> list = new ArrayList<Observer>();
        TeacherSubject teacherSubject = new TeacherSubject();
        teacherSubject.setList(list);
        teacherSubject.setInfo("work");
        Observer observer = new StudentObserver(teacherSubject);

        teacherSubject.setWork();
    }
}
