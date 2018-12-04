package com.bbear.example.thread;

/**
 * @author junxiongchen
 * @date 2018/11/21
 */
public class SynchronizeDemo {
    public static void main(String[] args) {
        SynchronizeDemo synchronizeDemo = new SynchronizeDemo();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizeDemo.A();
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizeDemo.B();
            }
        });
        thread.start();
        thread1.start();
    }
    private synchronized void A(){
        System.out.println("A.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "A===");
    }
    private static synchronized void B(){
        System.out.println("B.....");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "B===");
    }
}
