/******************************* Java：零钱兑换 II *******************************/
// 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
//
//
// 示例 1: 
//
// 输入: amount = 5, coins = [1, 2, 5]
// 输出: 4
// 解释: 有四种方式可以凑成总金额:
// 5=5
// 5=2+2+1
// 5=2+1+1+1
// 5=1+1+1+1+1
// 
//
// 示例 2: 
//
// 输入: amount = 3, coins = [2]
// 输出: 0
// 解释: 只用面额2的硬币不能凑成总金额3。
// 
//
// 示例 3: 
//
// 输入: amount = 10, coins = [10] 
// 输出: 1
// 
//
//
// 注意: 
//
// 你可以假设： 
//
// 0 <= amount (总金额) <= 5000 
// 1 <= coin (硬币面额) <= 5000 
// 硬币种类不超过 500 种 
// 结果符合 32 位符号整数 
// 
// 👍 408 👎 0


/******************************* 题目思路 *******************************/
// 状态：          当前背包容量，物品 i
// 决策：          某件物品装与不装
// DP 数组：       `dp[i][j]` 表示若只使用前 `i` 个物品，当背包容量为 `j` 时，有 `dp[i][j]` 种办法刚好将背包装满
// 状态转移方程：
//                如果你不把这第 `i` 个物品装入背包，也就是说你不使用 `coins[i-1]` 这个面值的硬币，那么凑出面额 `j` 的
//                方法数 `dp[i][j]` 应该等于 `dp[i-1][j]`，继承之前的结果。
//                如果你把这第 `i` 个物品装入了背包，也就是说你使用 `coins[i-1]` 这个面值的硬币，那么 `dp[i][j]` 应该等于 `dp[i][j-coins[i-1]]`。
//                也就是说：
//                  $$
//                  dp[i][j] = \begin{cases}
//                  dp\big[i-1\big]\big[j\big]\ +\ dp\big[i\big]\big[j - coins[i-1]\big], \quad & j \geq coins[i-1] \\\\
//                  dp[i-1][j], \quad & j < coins[i-1]
//                  \end{cases}
//                  $$
// 边界：          初始时，`dp[0][...] = 0` 以及 `dp[...][0] = 1`
// 压缩：          dp 状态的转移只与 `dp[i][...]` 和 `dp[i-1][...]` 有关，第一维循环可用迭代替代

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P518_CoinChange2 {
    public static void main(String[] args) {
        Solution solution = new P518_CoinChange2().new Solution();
        // TO TEST
        int amount = 5;
        int[] coins = {1, 2, 5};
        int answer = solution.change(amount, coins);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount+1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int x = coin; x < amount + 1; x++) {
                    dp[x] += dp[x - coin];
                }
            }

            return dp[amount];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(N×amount)。其中 N 为 coins 数组的长度。
// 空间复杂度：O(amount)，dp 数组使用的空间。