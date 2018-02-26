package com.bbear.example.test.thread;

import java.lang.Thread;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * list 创建线程安全的list
 * @author junxiongchen
 * @date 2018/02/26
 */
public class ThreadList {
    public static void main(String[] args) {
        final List<Integer> list1 = new Vector<>();
        for (int i = 0; i < 5; i++) {
            list1.add(i);
        }
        final List<Integer> list = Collections.synchronizedList(list1);
        new Thread(new Runnable(){
            @Override
            public void run() {
                //遍历list,虽然Vector,(Collections.synchronizedList)是线程安全的，但是在遍历的时候，
                // synchronize是没有线程控制的，所以也是线程不安全的
                synchronized (list) {
                    for (int item : list) {
                        System.out.println("元素:" + item);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.remove(4);
                System.out.println("list.remove(4)" + list);
            }
        }).start();
    }
}
