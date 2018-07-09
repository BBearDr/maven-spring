package com.bbear.example.test.thread;

public class Tradition {
    public static void main(String[] args) {
        final Option option = new Option();
        new java.lang.Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < 10; i++) {
                    option.main(i);
                }
            }
        }).start();
        new java.lang.Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < 10; i++) {
                    option.sub(i);
                }
            }
        }).start();
    }
    static class Option {
        boolean flag = true;
        public synchronized void main(int nThread){
            while(flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i < 5; i++) {
                System.out.println("main:" + i + "," + java.lang.Thread.currentThread().getName() + nThread);
            }
            flag = true;
            this.notify();
        }
        public synchronized void sub(int nThread){
            while(!flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 1; i < 6; i++) {
                System.out.println("sub:" + i + "," + java.lang.Thread.currentThread().getName() + nThread);
            }
            flag = false;
            this.notify();
        }
    }
}
