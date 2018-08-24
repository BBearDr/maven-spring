package com.bbear.example.lintCode;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;

/**
 * 寻找第K大的元素
 * @author junxiongchen
 * @date 2018/08/24
 */
public class SearchK {

    public static void main(String[] args) {
        int[] nums = {11,21,6, 9, 3, 7, 4,10,8};
        SearchK searchK = new SearchK();
        int solution = searchK.solution(nums, 0, nums.length - 1, nums.length-9);
        System.out.println(solution);
     //   System.out.println(Arrays.toString(nums));
        
    }

    private int solution(int[] nums, int start, int end,int k) {
        int i = start;
        int j = end;
        int privot = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= privot) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            while (i < j && nums[i] < privot) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = privot;
        System.out.println(Arrays.toString(nums));
        if (i == k) {
            return nums[i];
        }
        if (i > k) {
            return solution(nums, start, i - 1, k);
        }
        if (i < k) {
            return solution(nums, i + 1, end, k);
        }
        return 0;
    }
}
