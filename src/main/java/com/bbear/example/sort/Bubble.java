package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *  每一轮的比较将会找出最大的数
 * @author junxiongchen
 * @date 2018/07/31
 */
public class Bubble {
    public static void main(String[] args) {
        solution(Numbers.SORT_NUM);
    }

    private static void solution(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j + 1] = temp;
                }
                System.out.println(Arrays.toString(nums));
            }
        }
    }
}
