package com.bbear.example.util.singleton;

/**
 * 双重校验，保证多线程的安全
 *
 * @author junxiongchen
 * @date 2018/07/23
 */
public class DoubleCheck {
    private DoubleCheck() {
    }
    private static DoubleCheck singleton ;

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
