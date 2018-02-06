package com.bbear.example.util.observer_java;

/**
 * @author junxiongchen
 * @date 2018/01/30
 */
public class Test {
    public static void main(String[] args) {
        TeacherSubject t = new TeacherSubject();
        StudentObserver s = new StudentObserver(t);
        t.setWork("Home work");
    }
}
