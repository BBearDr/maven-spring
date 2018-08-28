package com.bbear.example.lintCode;

import com.bbear.example.sort.Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 -- 深度优先搜索
 *
 * @author junxiongchen
 * @date 2018/08/28
 */
public class AllSort {
    public static void main(String[] args) {
        AllSort allSort = new AllSort();
        int[] sortNum = {4, 6, 8};
        List<List<Integer>> permute = allSort.permute(sortNum);
        System.out.println(permute);
    }

    private List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, result,new ArrayList<Integer>(),new boolean[nums.length]);
        return result;
    }

    /**
     * 使用深度优先搜索算法dfs
     * 对已经排序过的元素进行标记
     *
     * @param nums  待排序
     * @param result 集合
     * @param permutation 排序的集合之一
     * @param visited  是否标记
     */
    private void dfs(int[] nums, List<List<Integer>> result,List<Integer> permutation,boolean[] visited) {
        //排序完成赋值，注意：需要重新new，因为对象是一个，会导致值覆盖
        if (nums.length == permutation.size()) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //标记元素
            permutation.add(nums[i]);
            visited[i] = true;
            //递归深度搜索
            dfs(nums, result, permutation, visited);
            //一种组合搜索完成之后，删除标记和元素，进行重新排序
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
