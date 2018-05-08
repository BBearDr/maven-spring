package com.bbear.example.lintCode;

/**
 * 合并两个有序数组
 * 思路：定义一个新的数组，将两个数组从后往前进行比较
 *
 * @author junxiongchen
 * @date 2018/04/26
 */
public class MergeOrderList {
    public static void main(String[] args) {
        int arr1[] = {1,4,5,7,10,11,15};
        int arr2[] = {11,17};
        solution(arr1,arr2);
    }

    private static void solution(int[] int1, int[] int2) {
        int len1 = int1.length;
        int i = len1 - 1;
        int len2 = int2.length;
        int j = len2 - 1;
        int lenSum = len1 + len2;
        Integer[] arr = new Integer[lenSum];
        lenSum--;
        while (i >= 0 && j >= 0) {
            if (int1[i] >= int2[j]) {
                arr[lenSum--] = int1[i];
                i--;
            } else if (int1[i] < int2[j]) {
                arr[lenSum--] = int2[j];
                j--;
            }
        }
        //将剩余的元素插入
        if (i > j) {
            while (i >= 0) {
                arr[lenSum--] = int1[i--];
            }
        }
        if (i < j) {
            while (j >= 0) {
                arr[lenSum--] = int2[j--];
            }
        }
        for (int i1 = 0; i1 < arr.length; i1++) {
            System.out.print(arr[i1]+",");
        }
    }
}
