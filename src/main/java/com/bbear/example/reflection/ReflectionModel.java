package com.bbear.example.reflection;


/**
 * @author chenjunxiong <chenjunxiong@kuaishou.com>
 * Created on 2021-01-24
 * 反射原理 demo 的基础类
 */
public class ReflectionModel {

    public void getFunction1() {
        System.out.println("this is getFunction1");
    }

    public String getFunction2() {
        return "this is getFunction2";
    }

    public String getFunction3(String param) {
        return "this is getFunction3" + param;
    }

    public String getFunction3(int param1, int param2) {

        return "this is getFunction4" + param1 + ";" + param2;
    }
}
