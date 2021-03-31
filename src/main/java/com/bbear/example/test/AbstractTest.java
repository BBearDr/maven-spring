package com.bbear.example.test;

/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-03-09
 */
public abstract class AbstractTest {

    public void execute() {
        System.out.println("this is abstract class");
        boolean doSingle = doSingle();
        System.out.println(doSingle);
        String name = getName();
        System.out.println(name);
    }

    public abstract boolean doSingle();

    public abstract String getName();
}
