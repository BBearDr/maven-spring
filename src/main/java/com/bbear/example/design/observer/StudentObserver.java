package com.bbear.example.design.observer;

/**
 * @author junxiongchen
 * @date 2018/01/30
 */
public class StudentObserver implements Observer {
    private String info;

    public StudentObserver(TeacherSubject teacherSubject) {
        this.info = teacherSubject.getInfo();
        teacherSubject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("teacherSubject notify student:"+info);
    }
}
