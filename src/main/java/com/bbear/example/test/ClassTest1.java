package com.bbear.example.test;

/**
 * 类的执行顺序
 * 静态代码块，静态方法，谁在前先执行谁
 * 实例块如果有被调用就执行
 * 最后执行构造器
 *
 * @author junxiongchen
 * @date 2018/02/27
 */
public class ClassTest1 {
    public static int a = 0;
    public int b = get();

    static {
        System.out.println("static : " + 22);
    }
    public ClassTest1() {
        System.out.println("constion :" + b);
    }

    public static int getInstance(){
        System.out.println("getInstance :" + a);
        return 0;
    }
    private int get(){
        System.out.println("get :" + b);
        return b;
    }
    public static int c = getInstance();

    public static void main(String[] args) {
        new ClassTest1();
    }
}
