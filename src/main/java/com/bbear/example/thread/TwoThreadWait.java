package com.bbear.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 循环打印奇数偶数
 * @author junxiongchen
 * @date 2018/10/17
 */
public class TwoThreadWait {
    private boolean flag = false;
    private  int start = 1;
    private static ExecutorService executorService = new ThreadPoolExecutor(2,3,10L, TimeUnit.SECONDS,new SynchronousQueue<>(),new ThreadPoolExecutor.AbortPolicy());
    public static void main(String[] args) {
        TwoThreadWait twoThreadWait = new TwoThreadWait();
        executorService.execute(new OuThread(twoThreadWait));
        executorService.execute(new jiThread(twoThreadWait));
        executorService.shutdown();
    }
    private static class  OuThread implements Runnable {
        private TwoThreadWait twoThreadWait;

        public OuThread(TwoThreadWait twoThreadWait) {
            this.twoThreadWait = twoThreadWait;
        }

        @Override
        public void run() {
            while (twoThreadWait.start <= 100) {
                synchronized (TwoThreadWait.class) {
                    System.out.println("偶数线程抢到线程了");
                    if (twoThreadWait.flag) {
                        System.out.println(Thread.currentThread().getName() + "偶数线程打印---->" + twoThreadWait.start);
                        twoThreadWait.start++;
                 //       System.out.println(Thread.currentThread().getName() + "偶数增加1 -->" + twoThreadWait.start);
                        twoThreadWait.flag = false;
                        TwoThreadWait.class.notify();
                    } else {
                        try {
                            TwoThreadWait.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    private  static class jiThread implements Runnable{
        private TwoThreadWait twoThreadWait;

        public jiThread(TwoThreadWait twoThreadWait) {
            this.twoThreadWait = twoThreadWait;
        }

        @Override
        public void run() {
            while (twoThreadWait.start <= 100) {
                synchronized (TwoThreadWait.class) {
                    System.out.println("奇数线程抢到了");
                    if (!twoThreadWait.flag) {
                        System.out.println(Thread.currentThread().getName() + "奇数线程打印--->" + twoThreadWait.start);
                        twoThreadWait.start++;
                   //     System.out.println(Thread.currentThread().getName() + "奇数线程增加1：" + twoThreadWait.start);
                        twoThreadWait.flag = true;
                        TwoThreadWait.class.notify();
                    } else {
                        try {
                            TwoThreadWait.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
