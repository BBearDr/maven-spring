package com.bbear.example.lintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对存在重复元素的数组，进行全排序
 *
 * @author junxiongchen
 * @date 2018/03/06
 */
public class PermutationsDouble {
    public static void main(String[] args) {
        PermutationsDouble t = new PermutationsDouble();
        int[] num = {1, 2, 2};
        List<List<Integer>> lists = t.permuteUnique(num);
        System.out.println(lists);
    }

    private List<List<Integer>> permuteUnique(int[] nums) {

        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        //用作标记该下标数是否组合过
        int[] visited = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            visited[i] = 0;
        }
        helper(visited,results,new ArrayList<Integer>(),nums);
        return results;
    }

    private void helper(int[] visited, ArrayList<List<Integer>> results, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            System.out.println(list);
            ///这里存的是list的记录，不能直接写list，这样存的是指向
//            results.add(list);
            results.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1 || (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = 1;
            helper(visited, results, list, nums);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }


}
