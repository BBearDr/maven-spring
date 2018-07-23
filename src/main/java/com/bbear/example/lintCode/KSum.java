package com.bbear.example.lintCode;

/**
 * 动态规划问题：
 *      01背包：每个元素要么出现，要么不出现，逆序遍历，前i个元素，在体积不超过V的情况下，所能达到的最大值，初始值为0
 *      完全背包：每个元素可以出现无数次，顺序遍历，在前i个元素中只要出现刚好为V的情况下，则是最大值，初始值不存在，dp[0]
 *      多重背包：拆分转化为01背包
 * @author junxiongchen
 * @date 2018/07/19
 */
public class KSum {
    public static void main(String[] args) {
        // k= {1,4} ,{2,3} target = 5
        int[] A = {1, 2, 3, 4};
        solution(A, 3, 9);
    }

    /**
     * 转移方程
     * dp(i, j, k) = dp(i-1, j, k) + dp(i-1, j-1, k-A[ i ])。（即取与不取A[i]的方案数之和，类似于01背包）
     * 注意：应该采用倒序的方式，因为如果顺序的话，之前的数会被之后的值所覆盖
     *           由于动态规划是在确定最优解的前提下往回找解的构成，之前的数据已经被覆盖，所以无法找到解的构成，只能列出解的多少方案
     * @param A 数组
     * @param k 组成的元素个数
     * @param target 组成元素的和
     */
    private static void solution(int[] A ,int k, int target) {
        int len = A.length;
        int[][] dp = new int[k + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j=k;j>=1;--j) {
                for(int s = target;s>=A[i-1];--s) {
                    dp[j][s] += dp[j - 1][s - A[i - 1]];
                    System.out.println("dp j:" + j + ",s:" + s + ",dp :" + dp[j][s]);
                }
            }
        }
        System.out.println(dp[k][target]);
    }
}
