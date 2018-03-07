package com.bbear.example.lintCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列 [1,2,3] 给出所有排列组合
 * 使用递归，每个数作为第一个数依次递归，当存在相同数跳过。
 * list.remove()是为了重新组合下一个数
 *
 * @author junxiongchen
 * @date 2018/03/05
 */
public class Test4 {
    public static void main(String[] args) {
        int[] num = {1,2,3};
        Test4 t = new Test4();
        List<List<Integer>> permute = t.permute(num);
        System.out.println(permute);
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        if (nums.length == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        ArrayList<Integer> list = new ArrayList<>();
        dfs(res, list, nums);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> list, int[] nums) {
        int n = nums.length;
        if (list.size() == n) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            dfs(res, list, nums);
            list.remove(list.size() - 1);
        }
    }


}
