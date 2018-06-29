package com.bbear.example.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 对Map中的value进行排序
 *
 * @author junxiongchen
 * @date 2018/06/29
 */
public class CompareMapValue {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        ValueComparator valueComparator = new ValueComparator(map);
        //TreeMap中实现了Comparator接口
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(valueComparator);
        map.put(1, 15);
        map.put(2, 16);
        map.put(3, 12);
        map.put(4, 11);
        map.put(5, 16);
        System.out.println("未排序的：" + map.toString());
        treeMap.putAll(map);
        System.out.println("排序的：" + treeMap.toString());
    }
}

class ValueComparator implements Comparator<Integer> {

    Map<Integer, Integer> map;

    public ValueComparator(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        if (map.get(o1) >= map.get(o2)) {
            return -1;
        } else {
            return 1;
        }
    }
}