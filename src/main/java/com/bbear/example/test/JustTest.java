package com.bbear.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junxiongchen
 * @date 2018/03/05
 */
public class JustTest {
    public static void main(String[] args) {
        JustTest j = new JustTest();
        List<List<Integer>> ls = new ArrayList<>();
        j.help(ls, new ArrayList<Integer>());
        System.out.println(ls);
    }
    private void help(List<List<Integer>> ls,List<Integer> list){
        list.add(1);
        list.add(2);
        list.add(3);
        ls.add(list);
    }
}
