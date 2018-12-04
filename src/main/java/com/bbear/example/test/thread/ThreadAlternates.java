package com.bbear.example.test.thread;


import java.lang.Thread;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junxiongchen
 * @date 2018/07/09
 */
public class ThreadAlternates {

   // public static void main(String[] args) {
/*        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<> (3), new ThreadPoolExecutor.AbortPolicy());*/
        public static volatile char now;

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要循环打印的字符串：");
        String str = scanner.nextLine();
        while (str.length() < 1){
            System.out.println("输入有误，请重新输入：");
            str = scanner.nextLine();
        }
        char[] chars = str.toCharArray();

        System.out.println("请输入循环打印的次数：");
        int temp = scanner.nextInt();
        while (temp < 1){
            System.out.println("输入有误，请重新输入：");
            temp = scanner.nextInt();
        }*/
        char[] chars = "asdf".toCharArray();
        final int n = 2;	//匿名内部类中只能访问方法中的常量



        for (int j = 0; j < chars.length; j++) {
            final int t = j;//匿名内部类中只能访问方法中的常量
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    for (int i = 0; i < n; i++) {//用于控制输出的次数
                        while (now != chars[t]) {//循环等待now指向该线程该输出的字符
//                           System.out.print(Thread.currentThread().getName()  + ":" +now);
                        }
                        System.out.print(chars[t]);//输出字符
                        if (t + 1 < chars.length)//修改now值，指向下一个该输出的字符
                            now = chars[t + 1];
                        else
                            now = chars[0];
                    }
                }
            });
            thread.start();
        }
        now = chars[0];
    }
}