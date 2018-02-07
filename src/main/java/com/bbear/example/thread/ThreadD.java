package com.bbear.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junxiongchen
 * @date 2018/02/07
 */
public class ThreadD {
    private volatile int count = 1;
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(1024));
        ThreadD threadD = new ThreadD();
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(new add(threadD));
        }
        threadPoolExecutor.shutdown();
    }

    public void add() {
        while (true) {
            synchronized (this) {
                notify();
                if (count <= 10) {
                    count++;
                } else {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + ":" + count);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class add implements Runnable {
    ThreadD threadD;

    public add(ThreadD threadD) {
        this.threadD = threadD;
    }

    @Override
    public void run() {
        threadD.add();
    }
}
