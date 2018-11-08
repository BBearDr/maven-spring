package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 思路 ： 挖坑填数+分治法。
 *
 * @author junxiongchen
 * @date 2018/07/31
 */
public class Quick {
    public static void main(String[] args) {
        int[] sortNum = Numbers.SORT_NUM;
        solution1(sortNum, 0, sortNum.length-1);
        System.out.println(Arrays.toString(sortNum));
    }

    /**
     * 挖坑填数，找到一边立即交换
     * 基准数，分别从右向左找出比基准数小的第一个数，将数字填到基准数的位置
     *               从左向右找出比基准数大的第一个数，将数字填到上述的位置
     *         重复上述步骤
     *
     * @param nums 排序数组
     * @param start 起始位置
     * @param end 结束位置
     */
    private static void solution(int[] nums, int start, int end) {
        if (start < end) {
            int i = start;
            int j = end;
            int pivot = nums[start];
            while (i < j) {
                //从右向左寻找比基准数小的第一个数
                while (i < j && pivot <= nums[j]) {
                    j--;
                }
                if (i < j) {
                    nums[i] = nums[j];
                    i++;
                }
                //从左向右寻找比基准数大的第一个数
                while (i < j && pivot > nums[i]) {
                    i++;
                }
                if (i < j) {
                    nums[j] = nums[i];
                    j--;
                }
            }
            //while结束，i= j,基准数位于中间
            nums[i] = pivot;
            System.out.println(Arrays.toString(nums) + ",left:" + start + ",right:"+end);
            //一轮结束之后基准数的左边都是比基准数小的，
            solution(nums, start, i - 1);
            //右边都是比基准数大的
            solution(nums, i + 1, end);
        }

    }

    /**
     * 更类似于比较交换，首先从两端找到符合条件的数字之后在进行交换
     * @param nums
     * @param start
     * @param end
     */
    private static void solution1(int[] nums,int start,int end) {
        if (start > end) {
            return ;
        }
        int left = start;
        int right = end;
        int pivot = nums[start];
        while (left < right) {
            /**
             * 必须要保证右边的判断在左边的之前，
             * 否则会出现，最后相遇的数字比基准数大，在交换的时候导致基准数左边出现比基准数大的数字
             */
            while (left < right && nums[right] >=pivot) {
                right--;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        //此时left == right，交换基准位置和相遇位置上的数字
        nums[start] = nums[left];
        nums[left] = pivot;
        System.out.println(Arrays.toString(nums) + ",left:" + start + ",right:"+end);
        solution1(nums, start, left - 1);
        solution1(nums,left+1,end);
    }
}
