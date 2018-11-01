package com.bbear.example.lintcode;

import java.util.Arrays;

/**
 * 寻找第K大的元素
 *
 * @author junxiongchen
 * @date 2018/08/24
 */
public class SearchK {

    public static void main(String[] args) {
        int[] nums = {11, 21, 6, 9, 3, 7, 4, 10, 8};
        SearchK searchK = new SearchK();
//        int solution = searchK.solution(nums, 0, nums.length - 1, nums.length-9);
        //   System.out.println(Arrays.toString(nums));
        searchK.solution2(nums, 4);
//        System.out.println(Arrays.toString(nums));
    }

    /**
     * 使用快排思路进行查找
     * 第一次排序完成之后基准值的位置是在目标值下标的左侧还是右侧
     * 如果是左侧就只是对左侧的数组进行排序。反之亦然
     * 因为快排的复杂度是O(NlogN),但是只对一边进行排序所以复杂度变为O(N)
     *
     * @param nums
     * @param start
     * @param end
     * @param k
     * @return
     */
    private int solution(int[] nums, int start, int end, int k) {
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

    /**
     * 使用二叉树，最小堆顶来进行查找，查找K次就是K元素
     * 对大小为N的数组构建二叉堆的算法复杂度是O(N)。然后每次下滤的算法复杂度是O(logN)，一共下滤K次，算法复杂度是O(N+K*logN)。
     * 该算法比较适合数量比较大的数组
     *
     * @param nums
     * @param k
     */
    private void solution2(int[] nums, int k) {
        //先对每个非叶子结点进行构造 小堆顶
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }

        //调整数组顺序
        for (int j = nums.length - 1; j >= nums.length - k; j--) {
            swap(nums, 0, j);
            if (k + j == nums.length) {
                break;
            }
            adjustHeap(nums, 0, j);
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(nums[nums.length - k]);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void adjustHeap(int[] nums, int i, int length) {
        int temp = nums[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && nums[k] > nums[k + 1]) {
                k++;
            }
            if (nums[k] < temp) {
                nums[i] = nums[k];
                i = k;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }
}
