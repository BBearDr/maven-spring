package com.bbear.example.design.singleton;

/**
 * 双重校验，保证多线程的安全
 *
 * @author junxiongchen
 * @date 2018/07/23
 */
public class DoubleCheck {
    private DoubleCheck() {
    }

    /**
     * 指令重排-JVM优化
     * volatile 使用解释： 因为在类加载的时候，是有三个过程，
     * 1.singleton分配内存对象 2.初始化成员变量 3.singleton对象指向内存
     * 但是JVM在加载的时候无法保证后面2步的执行顺序，1-2-3 或者1-3-2
     * 当先执行3的时候，返回对象是非null，此时会报错
     */
    private volatile static DoubleCheck singleton ;

    public static DoubleCheck getInstance(){
        if (singleton == null) {
            synchronized (DoubleCheck.class) {
                if (singleton == null){
                    singleton = new DoubleCheck();
                }
            }
        }
        return singleton;
    }
}
