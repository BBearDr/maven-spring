package com.bbear.example.test;

/**
 * @author junxiongchen
 * @date 2018/07/02
 */
public class InterTest {
    static {
        System.out.println("InterTest static");
    }
    private int i = getNum();
    public InterTest() {
        test();
    }
    private int getNum(){
        System.out.println(" InterTest getNum ");
        return 1;
    }
    public void test(){

    }
}
