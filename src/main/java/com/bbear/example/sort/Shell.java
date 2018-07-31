package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 直接插入排序的升级，是递减增量排序算法。非稳定排序
 * 改进方法
 * 1.插入排序对几乎排序好的算法操作时，效率高，可以达到线性排序的效率
 * 2.效率相对来说是低效的因为，每次只能移动一位数据
 *
 * @author junxiongchen
 * @date 2018/07/30
 */
public class Shell {
    public static void main(String[] args) {
        insertSort(Numbers.SORT_NUM);
    }

    /**
     * 希尔排序：根据将数组长度为len = k/2,从后向前依次进行比较
     *                  每次循环一轮之后对k再进行一次k/2，知道len=1为止
     * @param nums
     */
    private static void insertSort(int[] nums) {
        //数组的长度
        int LEN_STATIC = nums.length;
        //比较长度
        int len = nums.length;
        while (len != 0) {
            len = len / 2;
            for (int i = 0; i < len; i++) {
                for (int j = i + len; j < LEN_STATIC; j += len) {
                    //最左边第一个元素
                    int k = j - len;
                    //插入的元素
                    int temp = nums[j];
                    //依次向前对len的数据进行比较。如果大于目标数则进行交换
                    while (k >= 0 && nums[k] > temp) {
                        nums[k + len] = nums[k];
                        k -= len;
                    }
                    //将目标数赋予最后一次位置上的数
                    nums[k + len] = temp;
                    System.out.println(Arrays.toString(nums));
                }

            }
        }
    }
}
