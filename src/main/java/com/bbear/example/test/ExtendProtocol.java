package com.bbear.example.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/03/08
 */
public class ExtendProtocol extends ImplProtocol {
    public ExtendProtocol(List<String> list) {
        super(list);
        System.out.println("construct:"+list.size());
    }
    public static void main(String[] args) {
        ExtendProtocol extendProtocol = new ExtendProtocol(new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("123:"+list.get(i));
        }
        ImplProtocol implProtocol = new ImplProtocol(new ArrayList<String>());
        List<String> export = implProtocol.export();
        System.out.println(export);
    }

}
