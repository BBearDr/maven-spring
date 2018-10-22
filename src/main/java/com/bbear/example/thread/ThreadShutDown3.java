package com.bbear.example.thread;

import java.util.concurrent.*;

/**
 * @author junxiongchen
 * @date 2018/09/17
 */
public class ThreadShutDown3 {
    private static ExecutorService es = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
/*        Future<?> submit = es.submit(new Runnable() {
            @Override
            public void run() {
               *//* try {
                    Thread.sleep(6000);
                    System.out.println(Thread.currentThread().getName() + ":" + 555);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*//*
                System.out.println(1);
                int a = Integer.valueOf("ac");
            }
        });
        if (!es.awaitTermination(1, TimeUnit.SECONDS)) {
            submit.cancel(true);
        }*/

        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"acb");
                    int a = Integer.valueOf("ac");
                } catch (Exception e) {
                    System.out.println(12);
                }
            }
        });

        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":"+333);
            }
        });

        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":"+355);

            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":"+3551);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.shutdown();

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) es;
        while (true) {
            System.out.println("当前排队的线程数" + threadPoolExecutor.getQueue().size());
            System.out.println("当前活动的线程数"+threadPoolExecutor.getActiveCount());
            System.out.println("执行完线程数" + threadPoolExecutor.getCompletedTaskCount());
            System.out.println("总线程数" + threadPoolExecutor.getTaskCount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

