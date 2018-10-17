package com.bbear.example.thread;

import java.util.concurrent.*;

/**
 * @author junxiongchen
 * @date 2018/09/17
 */
public class ThreadShutDown3 {
    private static ExecutorService es = new ThreadPoolExecutor(2, 3, 1L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ":"+123);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) es;
        while (true) {
            System.out.println("当前排队的线程数" + threadPoolExecutor.getQueue().size());
            System.out.println("当前活动的线程数"+threadPoolExecutor.getActiveCount());
            System.out.println("执行完线程数" + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("总线程数" + threadPoolExecutor.getTaskCount());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

