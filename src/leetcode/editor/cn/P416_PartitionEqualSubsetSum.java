/******************************* Java：分割等和子集 *******************************/
// 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
//
// 示例 1： 
//
// 输入：nums = [1,5,11,5]
// 输出：true
// 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
//
// 示例 2： 
//
// 输入：nums = [1,2,3,5]
// 输出：false
// 解释：数组不能分割成两个元素和相等的子集。
// 
//
// 提示： 
//
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 动态规划 
// 👍 812 👎 0


/******************************* 题目思路 *******************************/
// Leedcode 给出的前言：
//          本题是经典的「NP 完全问题」，也就是说，如果你发现了该问题的一个多项式算法，那么恭喜你证明出了 P=NP，可以期待一下图灵奖了。
//          正因如此，我们不应期望该问题有多项式时间复杂度的解法。我们能想到的，例如基于贪心算法的「将数组降序排序后，依次将每个元素添加
//          至当前元素和较小的子集中」之类的方法都是错误的，可以轻松地举出反例。因此，我们必须尝试非多项式时间复杂度的算法，例如时间复
//          杂度与元素大小相关的动态规划。
//
// 问题转换（联系）：
//      给一个可装载重量为 $\frac{sum}{2}$ 的背包和 $N$ 个物品，每个物品的重量为 $nums[i]$。问，是否存在一种装法可以刚好装满背包？
//
// 思路：
//      状态：     当前背包容量
//      决策：     某件物品装与不装
//      DP 数组：  `dp[i][j]` 表示从数组的 `[0,i]` 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j。
//                  初始时，dp 中的全部元素都是 false。
//      状态转移方程：
//                  $$
//                  dp[i][j] = \begin{cases}
//                  dp\big[i-1\big]\big[j\big]\ \big|\ dp\big[i-1\big]\big[j - nums[i]\big], \quad j \geq nums[i] \\\\
//                  dp[i-1][j], \quad j < nums[i]
//                  \end{cases}
//                  $$
//      边界：     初始时，`dp[i][0] = true` 以及 `dp[0][nums[0]] = true`
//      压缩：     同上一题一样，在求 `dp[i][w]` 时，只需要用到上一行 dp 数组的值，所以可以压缩空间，
//                  将二维的 dp 数组压缩为一维，此时状态转移方程为：`dp[j] = dp[j] ∣ dp[j−nums[i]]`

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new P416_PartitionEqualSubsetSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartition(int[] nums) {
            int lenth = nums.length;

            // 求总和
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % 2 != 0) {
                return false;
            }

            // 初始化
            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;

            // 状态转移
            for (int i = 0; i < lenth; i++) {
                for (int j = target; j >= nums[i]; j--) {
                    // 只有这一种情况存在状态转移，其余情况都和之前的保持一样
                    dp[j] |= dp[j - nums[i]];
                }
            }
            return dp[target];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n x target)，两重循环
// 空间复杂度：O(target)，经过空间压缩后，dp 数组从二维变为一维，空间开销大大降低