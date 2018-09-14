package com.bbear.example.thread;


import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用synchronize，lock实现两个线程的轮流加减
 * @author junxiongchen
 * @date 2018/02/06
 */
public class ThreadDemo {

    private int count = 1;
    private boolean flag = false;
    public volatile int num = 1;
    Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition subCondition = lock.newCondition();

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        ThreadPoolExecutor executors = new ThreadPoolExecutor(2, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        ///尽量手动创建线程池
        /*for (int i = 0; i < 2; i++) {
            *//*Thread thread = new Thread(new thread1(threadDemo));
            Thread thread1 = new Thread(new thread2(threadDemo));
            thread.start();
            thread1.start();*//*
        }*/
        /**线程池上的循环只是增加了启动线程的数目，并非是将该线程执行多次*/
        executors.execute(new thread1(threadDemo));
        executors.execute(new thread2(threadDemo));
        executors.shutdown();
    }

    public void add() {
        lock.lock();
        try {
            for (int j = 0; j < 10; j++) {
                while (flag) {
                    try {
                        addCondition.await();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.println(Thread.currentThread().getName() + ":" + count);
                flag = true;
                subCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        lock.lock();
        try {
            for (int j = 0; j < 10; j++) {
                while (!flag) {
                    try {
                        subCondition.await();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                System.out.println(Thread.currentThread().getName() + ":" + count);
                flag = false;
                addCondition.signal();
            }
        }finally {
            lock.unlock();
        }
    }
}

class thread1 implements Runnable {
    ThreadDemo threadDemo;

    public thread1(ThreadDemo threadDemo) {
        this.threadDemo = threadDemo;
    }

    @Override
    public void run() {
        threadDemo.sub();
    }
}

class thread2 implements Runnable {
    ThreadDemo threadDemo;

    public thread2(ThreadDemo threadDemo) {
        this.threadDemo = threadDemo;
    }
    @Override
    public void run() {
        threadDemo.add();
    }
}
