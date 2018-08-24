package com.bbear.example.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的三分之一。
 *
 * @author junxiongchen
 * @date 2018/03/09
 */
public class MajorNumberSearchII {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(3);
        list.add(3);
        list.add(3);
        System.out.println(majorNumber(list));
    }
    private static int majorNumber(List<Integer> list){
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;
        //筛选出次数最多的两个数
        for (int i = 0; i < list.size(); i++) {
            if (candidate1 == list.get(i)) {
                count1++;
            } else if (candidate2 == list.get(i)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = list.get(i);
                count1++;
            } else if (count2 == 0) {
                candidate2 = list.get(i);
                count2++;
            } else{
                count1--;
                count2--;
            }
        }
        System.out.println(candidate1+";" + candidate2);
        //统计次数最多的元素
        count1 = count2 = 0;
        for (int i = 0; i < list.size(); i++) {
            if (candidate1 == list.get(i)) {
                count1++;
            } else if (candidate2 == list.get(i)) {
                count2++;
            }
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}
