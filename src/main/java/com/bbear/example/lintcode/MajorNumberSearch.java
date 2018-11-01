package com.bbear.example.lintcode;

/**
 * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
 *
 * @author junxiongchen
 * @date 2018/03/09
 */
public class MajorNumberSearch {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,2,3,3};
        System.out.println(majorNumber(nums));
    }
    private static int majorNumber(int[] nums){
        int result = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                result = nums[i];
                count = 1;
            } else if(result == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }
}
