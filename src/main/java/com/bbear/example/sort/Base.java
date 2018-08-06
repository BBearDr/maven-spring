package com.bbear.example.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序---是桶排序的扩展
 * 思路: 从个位数开始依次对十位数，百位数……上的数字进行比较，
 *           每个位数上的数字比较之后重新整合数组，这样就可以保证高位数相等的情况下，低位数上的数相对较高的在后面
 *           重复以上的操作，最后得到有序数组
 * @author junxiongchen
 * @date 2018/08/02
 */
public class Base {
    public static void main(String[] args) {
        Base base = new Base();
        int[] nums = {0, 53, 63, 38, 71, 125, 22, 11, 95, 36};
        base.solution(nums, nums.length);
        System.out.println(Arrays.toString(nums));
    }

    private void solution(int[] nums, int len) {
        //获得最大数以确定最大数位数
        int max = nums[0];
        for (int i = 0; i < len; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        //确定最大位数
        int time = 0;
        while (max != 0) {
            max /= 10;
            time++;
        }
        //创建数组相对应长度的集合,存放比较后的数
        List<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            list.add(arrayList);
        }
        //分别对各个位数上的数字进行比较
        for (int i = 0; i < time; i++) {
            for (int j = 0; j < len; j++) {
                //获取各个位数上的数字
                int x = (int) (nums[j] % Math.pow(10, i + 1) / Math.pow(10, i));
                ArrayList<Integer> arrayList = list.get(x);
                arrayList.add(nums[j]);
                list.set(x, arrayList);
            }
            //将所有的数组重新进行排序
            int count = 0;
            for (int k = 0; k < len; k++) {
                while (list.get(k).size() > 0) {
                    ArrayList<Integer> arrayList = list.get(k);
                    nums[count] = arrayList.get(0);
                    arrayList.remove(0);
                    count++;
                }
            }
        }
    }
}
