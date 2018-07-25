package com.bbear.example.lintCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，找出两个 不重叠 子数组使得它们的和最大。
 * 每个子数组的数字在数组中的位置应该是连续的。
 * 返回最大的和。
 * 样例
 * 给出数组 [1, 3, -1, 2, -1, 2]
 * 这两个子数组分别为 [1, 3] 和 [2, -1, 2] 或者 [1, 3, -1, 2] 和 [2]，它们的最大和都是 7
 *
 * @author junxiongchen
 * @date 2018/03/07
 */
public class MaxSubArraySearch2 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(-1);
        list.add(2);
        list.add(-1);
        list.add(2);
        int arrays = maxTwoSubArrays(list);
        System.out.println(arrays);
    }

    /**
     * 思路： 分别从左向右，从右向左进行遍历，对所有数进行相加，记录和最大子数组，
     *              然后left[i] + right[i+1] 进行相加，最大的即为最大值
     * @param nums
     * @return
     */
    private static int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int size = nums.size();
        int[] left = new int[size];
        int[] right = new int[size];
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            sum += nums.get(i);
            if (max <= sum) {
                max = sum;
            }
            if (sum < 0) {
                max = 0;
            }
            left[i] = max;
        }
        sum = 0;
        max = Integer.MIN_VALUE;
        for (int i = size - 1; i >= 0; i--) {
            sum += nums.get(i);
            if (max <= sum) {
                max = sum;
            }
            if (sum < 0) {
                max = 0;
            }
            right[i] = max;
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++) {
            max = Math.max(max, left[i] + right[i + 1]);
        }
        System.out.println("left:" + Arrays.toString(left) + ",right:" + Arrays.toString(right));
        return max;
    }
}
