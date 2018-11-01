package com.bbear.example.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集排序
 * {1,4,6} ==>[1], [1, 4], [1, 4, 6], [1, 6], [4], [4, 6], [6]
 * 使用深度优先搜索进行排序
 *
 * @author junxiongchen
 * @date 2018/08/31
 */
public class SubListSort {
    public static void main(String[] args) {
        SubListSort subListSort = new SubListSort();
        int[] nums = {1, 4, 4};
        List<List<Integer>> solution = subListSort.solution(nums);
        System.out.println(solution);
    }
    private List<List<Integer>> solution(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
//        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result, 0, new ArrayList<>());
        return result;
    }

    private void dfs(int[] nums, List<List<Integer>> result, int startIndex,List<Integer> subList) {
        result.add(new ArrayList<>(subList));
        System.out.println("subList:" + subList + ",startIndex:" + startIndex);
        for (int i = startIndex; i < nums.length; i++) {
            /**
             * 有元素重复时，去除重复的子集，
             * 去除思路：在重复元素的第二个元素即将出现在第二个位置的时候，判断是不是和前一个元素相同，相同则跳过
             */
       /*     if (i !=startIndex && nums[i] == nums[i - 1]) {
                continue;
            }*/
            subList.add(nums[i]);
            dfs(nums, result, i + 1, subList);
            subList.remove(subList.size() - 1);
        }
    }

}
