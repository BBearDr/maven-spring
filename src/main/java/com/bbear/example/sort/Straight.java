package com.bbear.example.sort;

import java.util.Arrays;

/**
 *  直接插入排序
 *   假设前面的n-1(n>=2) 数是已经排序好的数字，现将第n个数插入到已经排序好的n个数中，保持有序
 *
 * @author junxiongchen
 * @date 2018/07/30
 */
public class Straight {
    public static void main(String[] args) {
        insertSort(Numbers.SORT_NUM);
    }

    /**
     * 目标数，在有序的数组内从后向前进行遍历，比目标数大的向后移动一位，依次进行直到整个数组是有序的
     * @param nums 要排序的数组
     */
    private static void insertSort(int[]  nums) {
        //细节： 将长度单独拿出来可以提高效率
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int insertNum = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > insertNum) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j+1] = insertNum;
            System.out.println(Arrays.toString(nums));
        }
    }
}
