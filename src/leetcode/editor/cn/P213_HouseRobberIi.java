/******************************* Java：打家劫舍 II *******************************/
// 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着
// 第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被
// 小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
//
// 示例 1： 
//
// 输入：nums = [2,3,2]
// 输出：3
// 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 输入：nums = [1,2,3,1]
// 输出：4
// 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//    偷窃到的最高金额 = 1 + 3 = 4 。
//
//
// 示例 3： 
//
// 输入：nums = [0]
// 输出：0
// 
//
// 提示： 
//
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 710 👎 0


/******************************* 题目思路 *******************************/
// 同 “打家劫舍1” 一样，不提考察的也是不能偷相邻房屋的问题，唯一的不同在于此时房屋是环形排列
//
// 解决办法很简单：我们将环形房屋拆开，变成条形；
//      假设我们偷了  0 号屋子，我们就不能偷 n 号屋（首尾相连），此时能偷的房屋范围就是 [0, n-2]
//      假设我们没有偷 0 号屋子，我们就可以偷 n 号屋（首尾相连），此时能偷的房屋范围就是 [1, n-1]
//
// 这样，对这两段房屋，都调用 “打家劫舍1” 里的办法，求得最大值，再在这个最大值里求一个 max，那就是我们要的答案了

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P213_HouseRobberIi {
    public static void main(String[] args) {
        Solution solution = new P213_HouseRobberIi().new Solution();
        // TO TEST
        int[] nums = {2, 3, 2};
        int answer = solution.rob(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            int lenth = nums.length;
            if (lenth == 1) {
                return nums[0];
            } else if (lenth == 2) {
                return Math.max(nums[0], nums[1]);
            }
            return Math.max(robHelp(nums, 0, lenth - 2), robHelp(nums, 1, lenth - 1));
        }

        private int robHelp(int[] nums, int start, int end) {
            int first = nums[start];
            int second = Math.max(first, nums[start + 1]);

            for (int i = start + 2; i <= end; i++) {
                int temp = second;
                second = Math.max(first + nums[i], second);
                first = temp;
            }
            return second;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，两次遍历
// 空间复杂度 O(1)。