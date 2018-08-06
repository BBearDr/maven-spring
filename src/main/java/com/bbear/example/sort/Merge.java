package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 思路：数组两两合并，依次进行比较，然后交换位置，比较的个数是2倍增长的
 *
 *   时间复杂度
 *      归并排序的形式就是一颗二叉树，时间复杂度O(N*logN)
 *   稳定性
 *      假设数组中存在a[i]=a[j] ,若排序之前，a[i]在a[j]之前，排序之后还是保持前后位置不变，那么算法就是稳定的
 * @author junxiongchen
 * @date 2018/08/01
 */
public class Merge {
    /**
     * 将一个数组中的两个相邻有序区间合并成一个
     * <p>
     * 参数说明：
     * a -- 包含两个有序区间的数组
     * start -- 第1个有序区间的起始地址。
     * start2   -- 第1个有序区间的结束地址。也是第2个有序区间的起始地址。
     * end   -- 第2个有序区间的结束地址。
     */
    public static void merge(int[] a, int start, int start2, int end) {
        System.out.println(" start :" + start + ",start2:" + start2 + ",end:" + end);
        // tmp是汇总2个有序区的临时区域
        int[] tmp = new int[end - start + 1];
        // 第1个有序区的索引
        int i = start;
        // 第2个有序区的索引
        int j = start2 + 1;
        // 临时区域的索引
        int k = 0;

        while (i <= start2 && j <= end) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        while (i <= start2) {
            tmp[k++] = a[i++];
        }

        while (j <= end) {
            tmp[k++] = a[j++];
        }

        // 将排序后的元素，全部都整合到数组a中。
        for (i = 0; i < k; i++) {
            a[start + i] = tmp[i];
        }
        System.out.println(Arrays.toString(a));
        tmp = null;
    }

    /**
     * 归并排序(从上往下)
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     * start -- 数组的起始地址
     * endi -- 数组的结束地址
     */
    public static void mergeSortUp2Down(int[] a, int start, int end) {
        if (a == null || start >= end) {
            return;
        }

        int mid = (end + start) / 2;
        /**
         * 递归到两个序列中只有一个元素的时候，进行比较(步长为1，进行两两比较)并且赋值给临时数组
         * 而后依次进行增加两个序列的步长(+1)
         */
        // 递归排序a[start...mid]
        mergeSortUp2Down(a, start, mid);
        // 递归排序a[mid+1...end]
        mergeSortUp2Down(a, mid + 1, end);

        // a[start...mid] 和 a[mid...end]是两个有序空间，
        // 将它们排序成一个有序空间a[start...end]
        // System.out.println(" start :" + start + ",mid:" + mid + ",end:" + end);
        merge(a, start, mid, end);
    }


    /**
     * 对数组a做若干次合并：数组a的总长度为len，将它分为若干个长度为gap的子数组；
     * 将"每2个相邻的子数组" 进行合并排序。
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     * len -- 数组的长度
     * gap -- 子数组的长度
     */
    public static void mergeGroups(int[] a, int len, int gap) {
        int i;
        // 两个相邻的子数组的长度
        int twolen = 2 * gap;

        // 将"每2个相邻的子数组" 进行合并排序。
        for (i = 0; i + twolen - 1 < len; i += (twolen)) {
            merge(a, i, i + gap - 1, i + twolen - 1);
        }

        // 若 i+gap-1 < len-1，则剩余一个子数组没有配对。
        // 将该子数组合并到已排序的数组中。
        if (i + gap - 1 < len - 1) {
            merge(a, i, i + gap - 1, len - 1);
        }
    }

    /**
     * 归并排序(从下往上)
     * <p>
     * 参数说明：
     * a -- 待排序的数组
     */
    public static void mergeSortDown2Up(int[] a) {
        if (a == null) {
            return;
        }

        for (int n = 1; n < a.length; n *= 2) {
            mergeGroups(a, a.length, n);
        }
    }

    public static void main(String[] args) {
        int i;
        int a[] = {80, 30, 60, 40, 20, 10, 50, 70};

        System.out.printf("before sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.printf("\n");
        // 归并排序(从上往下)
      //  mergeSortUp2Down(a, 0, a.length - 1);
        // 归并排序(从下往上)
        mergeSortDown2Up(a);

        System.out.printf("after  sort:");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.printf("\n");
    }
}
