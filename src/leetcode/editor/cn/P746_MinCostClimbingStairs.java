/******************************* Java：使用最小花费爬楼梯 *******************************/
// 数组的每个索引作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i](索引从 0 开始)。
//
// 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。 
//
// 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。 
//
// 示例 1: 
//
// 输入: cost = [10, 15, 20]
// 输出: 15
// 解释: 最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15。
// 
//
// 示例 2: 
//
// 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
// 输出: 6
// 解释: 最低花费方式是从 cost[0] 开始，逐个经过那些 1，跳过 cost[3]，一共花费 6。
// 
//
// 注意： 
//
// 
// cost 的长度将会在 [2, 1000]。 
// 每一个 cost[i] 将会是一个 Integer 类型，范围为 [0, 999]。
// 
// Related Topics 数组 动态规划 
// 👍 412 👎 0


/******************************* 题目思路 *******************************/
// 由题，容易得到递推关系：f[i] = cost[i] + min(f[i-1], f[i-2])
// 那么，本题逻辑关系就和 T198（打家劫舍）很像。
// 有一个注意点：最终，上台阶要上到的是第 i+1 处，不是第 i 处

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P746_MinCostClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new P746_MinCostClimbingStairs().new Solution();
        // TO TEST
        int[] cost = {10, 15, 20};
        int answer = solution.minCostClimbingStairs(cost);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCostClimbingStairs(int[] cost) {

            if (cost.length == 0) return 0;
            else if (cost.length == 1) return cost[0];
            else if (cost.length == 2) return cost[1];
            else {
                int f0 = cost[0];
                int f1 = cost[1];
                int f = 0;

                for (int i = 2; i < cost.length; i ++) {
                    f = cost[i] + Math.min(f0, f1);
                    f0 = f1;
                    f1 = f;
                }
                return Math.min(f0, f1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)