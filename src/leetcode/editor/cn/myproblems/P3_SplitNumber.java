/******************************* Java：数的划分 *******************************/
// 将整数 n 分成 k 份，且每份不能为空，任意两个方案不能相同(不考虑顺序)。
//
// 例如：n=7，k=3，下面三种分法被认为是相同的。
// 1，1，5;
// 1，5，1;
// 5，1，1;
// 问有多少种不同的分法。
//
// 输入：n，k ( 6 < n ≤ 200，2 ≤ k ≤ 6 )
// 输出：一个整数，即不同的分法。

/******************************* 题目思路 *******************************/
// dp[i][j] 表示数字 i 分成 j 份总共有多少种分法。并且置初值 dp[0][0] = 1。
// 对于状态转移过程，我们分为两种情况进行讨论：
//      一种是至少有一份分配了数字 1，另一种是没有分配数字 1。
//      那么如果至少分配了一份是数字 1，那么剩下的分发就是 dp[i-1][j-1],即将数字 i-1 分为 j-1。
//      如果没有分配数字 1，那么我们一定可以将 j 份都提前放一个数字 1，剩下的就是 dp[i-j][j]，即将数字 i-j 分为 j 份

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.myproblems;

public class P3_SplitNumber {
    public static void main(String[] args) {
        int n = 7;
        int k = 3;
        int answer = new P3_SplitNumber().splitNumber(n, k);
        System.out.println(answer);
    }

    public int splitNumber(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i >= j) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-j][j];
                }
            }
        }
        return dp[n][k];
    }
}
