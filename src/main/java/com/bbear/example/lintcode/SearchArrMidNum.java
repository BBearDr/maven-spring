package com.bbear.example.lintcode;

/**
 * 两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，
 * 要求时间复杂度应为O(log (m+n))
 *
 * 思路:由于两个是有序数组，所以选择每个数组中的中位数进行比较，
 *  根据中位数大小决定取每个数组的中位数之前的数组，还是之后的数组
 *  然后在进行相同的比较，直到k=1时得出结果
 * @author junxiongchen
 * @date 2018/05/08
 */
public class SearchArrMidNum {
    public static void main(String[] args) {
        int[] A = {1, 9};
        int[] B = {4, 5, 8};
        double medianSortedArrays = findMedianSortedArrays(A, B);
        System.out.println(medianSortedArrays);

    }

    private static double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length + B.length;
        if (n % 2 == 0) {
            return (
                    findKth(A, 0, B, 0, n / 2) +
                            findKth(A, 0, B, 0, n / 2 + 1)
            ) / 2.0;
        }

        return findKth(A, 0, B, 0, n / 2 + 1);
    }

    // find kth number of two sorted array
    private static int findKth(int[] A, int startOfA, int[] B, int startOfB, int k) {
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }
        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }

        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }

        int halfKthOfA = startOfA + k / 2 - 1 < A.length
                ? A[startOfA + k / 2 - 1]
                : Integer.MAX_VALUE;
        int halfKthOfB = startOfB + k / 2 - 1 < B.length
                ? B[startOfB + k / 2 - 1]
                : Integer.MAX_VALUE;
        /**
         * 此处注意不能将k-k/2写成k/2会出现精度丢失
         */
        if (halfKthOfA < halfKthOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        } else {
            return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
        }
    }
}
