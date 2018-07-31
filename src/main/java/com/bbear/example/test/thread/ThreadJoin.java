package com.bbear.example.test.thread;

/**
 * @author junxiongchen
 * @date 2018/01/04
 */
public class ThreadJoin {
    public static void main(String[] args) {
       /* ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit();*/
    }
}
class Thread implements Runnable {
    @Override
    public void run() {

    }
}
class Thread1 implements  Runnable{
    @Override
    public void run() {

    }
}
class Thread2 implements  Runnable{
    @Override
    public void run() {
        Thread1 thread1 = new Thread1();
        System.out.println("t3");
    }
}
