package com.bbear.example.lintCode;

/**
 * 写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
 * 这个矩阵具有以下特性：
 * 每行中的整数从左到右是排序的。
 * 每一列的整数从上到下是排序的。
 * 在每一行或每一列中没有重复的整数。
 * 思路：在矩阵的右上角或者左下角作为起始点开始进行查找，
 *
 * @author junxiongchen
 * @date 2018/03/07
 */
public class MatrixSearch {
    public static void main(String[] args) {
        int[][] nums = {{1, 3, 5, 7},
                {2, 4, 7, 8},
                {3, 5, 9, 10}};
        int searchMatrix = searchMatrix(nums, 7);
        System.out.println(searchMatrix);
    }

    private static int searchMatrix(int[][] nums, int target) {
        int count = 0;
        int m = nums.length;
        int n = nums[0].length;
        int x = m - 1;
        int y = 0;
        while (x >= 0 && y < n) {
            if (nums[x][y] < target) {
                y++;
            } else if (nums[x][y] > target) {
                x--;
            } else {
                count++;
                y++;
                x--;
            }
        }
        return count;
    }
}
