package com.bbear.example.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 思路： 将数组构建成大顶堆(最大的元素在最上面),将最上面的结点和末尾元素进行交换
 *              依次进行，直到最后一个元素
 *时间复杂度 O(nlogn)
 * @author junxiongchen
 * @date 2018/07/30
 */
public class Heap {
    public static void main(String[] args) {
        int[] sortNum = Numbers.SORT_NUM;
        sort(sortNum);
   //     System.out.println(Arrays.toString(sortNum));
    }

    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        System.out.println(Arrays.toString(arr));
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            //重新对堆进行调整
            adjustHeap(arr, 0, j);
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr 排序数组
     * @param i 第一个非叶子结点
     * @param length 排序数组长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }

    /**
     * 交换方法
     * @param arr 数组
     * @param a 待交换a
     * @param b 待交换b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

