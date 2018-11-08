package com.bbear.example.lintcode;

import com.bbear.example.sort.Numbers;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 需求：从100个数字里头获取前25个小的数字
 * 构造大顶堆，取一个数字和顶数字进行比较，比顶数字小进行交换
 * 再进行构造大顶堆，循环最后是满足条件的数字
 *
 * @author junxiongchen
 * @date 2018/11/08
 */
public class Get25 {
    public static void main(String[] args) {
        int[] nums = Numbers.SORT_NUM;
        Get25 get25 = new Get25();
        get25.adjust(nums);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            int i = scanner.nextInt();
            //根据输入获得最小的前几个
            int num = nums[0];
            if (num > i) {
                nums[0] = i;
                System.out.println(Arrays.toString(nums)+":"+i);
                get25.adjust(nums);
            }
            get25.solution(nums);
            System.out.println(Arrays.toString(nums));
        }
        scanner.close();
    }

    private void solution(int[] nums) {

      //  System.out.println(Arrays.toString(nums));
        for (int i = nums.length-1; i >=0; i--) {
            //将最大数放到末尾
            swap(nums,0,i);
            //调整之后的大顶堆
            adjustHeap(nums, 0, i);
        }
    }
    private void adjust(int[] nums){
        //1.构造大顶堆，使非叶子结点大于子结点
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums,i,nums.length);
        }
    }

    private void swap(int[] nums, int i, int last) {
        int temp = nums[i];
        nums[i] = nums[last];
        nums[last] = temp;
    }

    private void adjustHeap(int[] nums, int k, int length) {
        //记录非叶子结点
        int temp = nums[k];
        //从第一个非叶子结点开始向寻找
        for (int i = 2 * k + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && nums[i] < nums[i + 1]) {
                i++;
            }
            if (nums[i] > temp) {
                nums[k] = nums[i];
                k = i;
            }else{
                //因为自下而上的寻找，所以若非叶子结点的子结点没有符合条件的可以直接跳出不必遍历
                break;
            }
        }
        nums[k] = temp;
    }
}
