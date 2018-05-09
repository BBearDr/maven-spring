package com.bbear.example.lintCode;

/**
 * 寻找峰值，使用二分法查找时间复杂度为logn
 *
 * @author junxiongchen
 * @date 2018/05/09
 */
public class SearchPeak {
    public static void main(String[] args) {
        int[] num = {1, 2, 1, 3, 4, 7, 6};
        int solution = solution(num);
        System.out.println(solution);
    }

    private static int solution(int[] num) {
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            //1. 计算出mid的位置
            int mid = start + (end - start) / 2;
            if (mid == 0) {
                return 1;
            }
            if (mid == num.length - 1) {
                return mid - 1;
            }
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1]) {
                return mid;
            } else if (num[mid] < num[mid - 1]) {
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }
}
