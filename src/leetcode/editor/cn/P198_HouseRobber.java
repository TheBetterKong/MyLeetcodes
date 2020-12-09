/******************************* Java：打家劫舍 *******************************/
// 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
// 被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
// 输出：4
// 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//    偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
// 输出：12
// 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//    偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1201 👎 0


/******************************* 题目思路 *******************************/
// 本题是典型的 “动态规划” 问题：
// 假设我们已经知道了 偷到第 n-1 和 n-2 间屋子 时能获取到的最大金额数为 f(n-1) 和 f(n-2)；
// 那么 偷到第 n 间屋子时的最大金额 f(n) = max( f(n-1), f(n-2)+nums[n] )
// 这样相当于递推关系就出来了

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P198_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new P198_HouseRobber().new Solution();
        // TO TEST
        int[] nums = {2,7,9,3,1};
        int answer = solution.rob(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;

            if (nums.length == 1)
                return nums[0];

            int[] maxget = new int[nums.length];
            maxget[0] = nums[0];
            maxget[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < nums.length; i++) {
                maxget[i] = Math.max(maxget[i-2] + nums[i], maxget[i-1]);
            }

            return maxget[nums.length - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，一次遍历
// 空间复杂度 O(1)。使用滚动数组，可以只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是 O(1)。
