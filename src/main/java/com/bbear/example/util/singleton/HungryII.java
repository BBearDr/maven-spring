package com.bbear.example.util.singleton;

/**
 * @author junxiongchen
 * @date 2018/07/23
 */
public class HungryII {
    public HungryII() {
    }
    private static HungryII singleton ;
    static {
        singleton = new HungryII();
    }
    public static HungryII getInstance() {
        return singleton;
    }
}
