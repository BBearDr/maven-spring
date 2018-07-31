package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 简单选择排序
 * 基本思想： 比较+交换，遍历所有元素，将所有元素都遍历完成之后再将最小的值和第一个元素进行交换
 * 和插入排序不同的是，插入排序是比较之后立即进行交换，而选择排序是比较所有之后在进行交换
 * @author junxiongchen
 * @date 2018/07/30
 */
public class EasySelect {
    public static void main(String[] args) {
        solution(Numbers.SORT_NUM);
    }

    private static void solution(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int index = i;
            int min = nums[i];
            //遍历所有的元素，求出最小的值
            for (int j = i + 1; j < len; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    index = j;
                }
            }
            //对最小的值进行交换
            nums[index] = nums[i];
            nums[i] = min;
            System.out.println(Arrays.toString(nums));
        }
    }
}
