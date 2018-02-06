package com.bbear.example.util.productAndConsume;


/**
 * @author junxiongchen
 * @date 2017/12/26
 */
public class Main {
    public static void main(String[] args) {
//        Storage storage = new Storage();
        StorageLock storage = new StorageLock();
        // 生产者对象
        Product p1 = new Product(storage);
        Product p2 = new Product(storage);
        Product p3 = new Product(storage);
        Product p4 = new Product(storage);
        Product p5 = new Product(storage);
        Product p6 = new Product(storage);
        Product p7 = new Product(storage);

        // 消费者对象
        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(80);

        // 设置消费者产品消费数量
        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        // 线程开始执行
        c1.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
}
