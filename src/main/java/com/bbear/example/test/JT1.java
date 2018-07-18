package com.bbear.example.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author junxiongchen
 * @date 2018/03/27
 */
public   class JT1 extends InterTest{
    static {
        System.out.println("JT1 static");
    }
    private String str = getStr();
    private int num ;

    public JT1() {
        System.out.println(" JT1 init:"+ num);
    }

    @Override
    public void test() {
        System.out.println("InterTets init");
    }

    private String getStr() {
        System.out.println("getStr init");
        return "qw";
    }
    public static void main(String[] args) {
        JT1 jt1 = new JT1();
    }
}
