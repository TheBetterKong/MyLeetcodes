/******************************* Java：组合总和 Ⅳ *******************************/
// 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
//
// 题目数据保证答案符合 32 位整数范围。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,2,3], target = 4
// 输出：7
// 解释：
// 所有可能的组合为：
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)
// 请注意，顺序不同的序列被视作不同的组合。
// 
//
// 示例 2： 
//
// 输入：nums = [9], target = 3
// 输出：0
// 
//
// 提示： 
//
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 1000 
// nums 中的所有元素 互不相同 
// 1 <= target <= 1000 
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？ 
// Related Topics 动态规划 
// 👍 442 👎 0


/******************************* 题目思路 *******************************/
// 注意，与 T518 对比，本题是顺序相关的完全背包问题，518 是顺序无关的完全背包问题
//
// 状态：  dp[x] 表示选取的元素和为 x 的方案数；
// 状态转移：
//      当 1 ≤ i ≤ target 时，如果存在一种排列，其中的元素之和等于 i，则该排列的最后一个元素一定是数组 nums 中的一个元素。
//      假设该排列的最后一个元素是 num，则一定有 num ≤ i，对于元素之和等于 i − num 的每一种排列，在最后添加 num 之后即可得
//      到一个元素之和等于 i 的排列，因此在计算 dp[i] 时，应该计算所有的 dp[i−num] 之和。
// 动态规划的做法：
//      初始化 dp[0] = 1；
//      遍历 i 从 1 到 target，对于每个 i，进行如下操作：
//      遍历数组 nums 中的每个元素 num，当 num ≤ i 时，将 dp[i−num] 的值加到 dp[i]。
//      最终得到 dp[target] 的值即为答案。
//
// 对比：顺序分析
//      因为外层循环是遍历从 1 到 target 的值，内层循环是遍历数组 nums 的值，在计算 dp[i] 的值时，nums 中的每个小于等于 i 的
//      元素都可能作为元素之和等于 i 的排列的最后一个元素。例如，1 和 3 都在数组 nums 中，计算 dp[4] 的时候，排列的最后一个元素
//      可以是 1 也可以是 3，因此 dp[1] 和 dp[3] 都会被考虑到，即不同的顺序都会被考虑到。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P377_CombinationSumIv {
    public static void main(String[] args) {
        Solution solution = new P377_CombinationSumIv().new Solution();
        // TO TEST
        int[] nums = {1,2,3};
        int target = 4;
        int answer = solution.combinationSum4(nums, target);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;

            for (int i = 1; i <= target; i++) {
                for (int num : nums) {
                    if (num <= i) {
                        dp[i] += dp[i -num];
                    }
                }
            }

            return dp[target];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(N×amount)。其中 N 为 coins 数组的长度。
// 空间复杂度：O(amount)，dp 数组使用的空间。