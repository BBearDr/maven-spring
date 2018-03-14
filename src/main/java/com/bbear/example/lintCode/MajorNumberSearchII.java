package com.bbear.example.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/03/09
 */
public class MajorNumberSearchII {
    public static void main(String[] args) {

        int[] num = {1,2,1,2,1,3,3,3,3};
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(majorNumber(list));
    }
    private static int majorNumber(ArrayList<Integer> list){
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;
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
