package com.bbear.example.lintCode;

/**
 * 在给定的数组中。计算出最大的和是多少
 *
 * @author junxiongchen
 * @date 2018/03/07
 */
public class MaxSubArraySearch {
    public static void main(String[] args) {
        int[] n = {-1, 2,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(n));
    }
    private static int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }

        return max;
    }
}
