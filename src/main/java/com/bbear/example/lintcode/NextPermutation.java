package com.bbear.example.lintcode;

import java.util.Arrays;

/**
 * 给出下一个数组的排列
 *
 * @author junxiongchen
 * @date 2018/03/21
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = {3,1, 2, 5};
        int[] ints = sol(nums);
        System.out.println(Arrays.toString(ints));
    }

    private static int[] sol(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return nums;
        }

        int len = nums.length;
        int flag = len - 1;
        boolean change = false;
        for (int i = len - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                flag = i;
                change = true;
                break;
            }
        }

        if (!change) {
            Arrays.sort(nums);
            return nums;
        }
        int temp = nums[flag];
        nums[flag] = nums[flag - 1];
        for (int i = flag; i < len; i++) {
            if (nums[i] > nums[flag - 1] && nums[i] < temp) {
                int p = temp;
                temp = nums[i];
                nums[i] = p;
            }
        }
        nums[flag - 1] = temp;
        for (int i = flag; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[i]) {
                    int m = nums[i];
                    nums[i] = nums[j];
                    nums[j] = m;
                }
            }
        }
        return nums;
    }
}
