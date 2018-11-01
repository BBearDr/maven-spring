package com.bbear.example.lintcode;

import java.util.Arrays;

/**
 * 丑数II
 * 只能是2,3,5作为因子的数字。1是最小的丑数
 *
 * 思路：因为235是最小的丑数，所以基础数乘以最小的三个丑数也是丑数
 *              num[L1]*2，num[L2]*3，num[L3]*5，取三个数中最小的数最为num[1]的丑数值
 *              此时该下标L1/L2/L3，在谁的下标上取值，谁+1，依次往后类推
 *
 * @author junxiongchen
 * @date 2018/08/13
 */
public class UglyNumberII {
    public static void main(String[] args) {
        int num = 20;
        int[] nums = new int[num];
        solution(num, nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 取最小值
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static int min(int a, int b, int c) {
        int min = a;
        if (min > b) {
            min = b;
        }
        if (min > c) {
            min = c;
        }
        return min;
    }

    private static void solution(int num, int[] nums) {
        int a2 = 0;
        int b3 = 0;
        int c5 = 0;
        nums[0] = 1;
        for (int i = 1; i < num; i++) {
            int ugly = min(nums[a2] * 2, nums[b3] * 3, nums[c5] * 5);
            if (ugly == nums[a2] * 2) {
                a2++ ;
            }
            if (ugly == nums[b3] * 3) {
                b3++ ;
            }
            if (ugly == nums[c5] * 5) {
                c5++ ;
            }
            nums[i] = ugly;
        }
    }
}
