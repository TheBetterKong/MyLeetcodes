/******************************* Java：零钱兑换 *******************************/
// 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
// 如果没有任何一种硬币组合能组成总金额，返回 -1。
//
// 你可以认为每种硬币的数量是无限的。 
//
//
// 示例 1： 
//
// 输入：coins = [1, 2, 5], amount = 11
// 输出：3
// 解释：11 = 5 + 5 + 1
//
// 示例 2： 
//
// 输入：coins = [2], amount = 3
// 输出：-1
//
//
// 示例 3： 
//
// 输入：coins = [1], amount = 0
// 输出：0
// 
//
// 示例 4： 
//
// 输入：coins = [1], amount = 1
// 输出：1
// 
//
// 示例 5： 
//
// 输入：coins = [1], amount = 2
// 输出：2
// 
//
// 提示： 
//
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 动态规划 
// 👍 1292 👎 0


/******************************* 题目思路 *******************************/
// 确定状态，状态变量，状态转移方程：
//      状态是随着时刻变化的，硬币数量不限，那就只有金额会变，于是将状态描述为 $dp(amount)$，状态变量 $amount$ 即当前凑出来得钱数目。那 DP 函数 $dp(amount)$ 的意义就是：凑出 amount 的钱，最少需要 $dp(amount)$ 枚硬币；
//      对于每个状态，改变它们的方式就是选取一枚硬币（对应三种情况），目标金额就会随着相应减少。
//          $dp(n) = min(dp[n - coin]+1\ |\ coin \in coins)$
//
// 确定最优策略：
//      硬币数量最少，就是上述式子里的 min 函数；
//
// 确定边界条件：
//      ⽬标⾦额为 0 时，所需硬币数量为 0；当⽬标⾦额 ⼩于 0 时，⽆解，返回 -1：
//
// 总结上述分析过程，最后得到完整的状态转移方程：
//        $$
//        dp(n) = \begin{cases}
//        -1, \quad n < 0 \\\\
//        0,  \quad n = 0 \\\\
//        min(dp[n - coin]+1\ |\ coin \in coins), \quad n > 0
//        \end{cases}
//        $$

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P322_CoinChange {
    public static void main(String[] args) {
        Solution solution = new P322_CoinChange().new Solution();
        // TO TEST
        int[] coins = {1, 2, 5};
        int amount = 11;
        int answer = solution.coinChange(coins, amount);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            // 假设硬币最小面额为 1，最多需要 amount 枚，因此不管如何，所需的金币都不可能达到 amount+1
            // 所以这里相当于初始化 dp 所有值都为不可能凑出
            Arrays.fill(dp, amount + 1);

            dp[0] = 0;

            for (int i = 0; i < amount + 1; i++) {
                for (int coin : coins) {
                    if (i - coin < 0) continue;
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }

            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(amount x n)，amount 为所需金额， n 为硬币的面额情况数
// 空间复杂度：O(amount)，dp 数组的大小