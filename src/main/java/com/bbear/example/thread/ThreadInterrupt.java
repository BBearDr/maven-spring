package com.bbear.example.thread;

/**
 * @author junxiongchen
 * @date 2018/11/26
 */
public class ThreadInterrupt {
    public static void main(String[] args) {
        ThreadInterrupt threadInterrupt = new ThreadInterrupt();
        threadInterrupt.solution1();
    }
    private void solution1(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
             int i = 0;
             while (i<10){
                 System.out.println(i++);
             }
            }
        });

        while (thread.isInterrupted()) {
            System.out.println("thread is isInterrupt");
            break;
        }
        thread.start();
    }
}
