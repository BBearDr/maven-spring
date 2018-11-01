package com.bbear.example.lintcode;

/**
 * 使用二分查找，找出指定的重复数字的第一个下标位置
 *
 * @author junxiongchen
 * @date 2018/03/06
 */
public class BinarySearchNum {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 3, 4, 5};
        int search = binarySearch(num, 3);
        System.out.println(search);
    }
    private static int binarySearch(int[] num,int target) {
        int left = 0;
        int right = num.length;
        int mid = (left + right) / 2;
        while (left < right) {
            if (num[mid] < target) {
                left = mid + 1;
            }
            if (num[mid] >= target) {
                right = mid;
            }
            System.out.println("left:"+left+",right:"+right);
            mid = (left + right) / 2;
        }
        return num[mid] == target ? mid : -1;
    }
}
