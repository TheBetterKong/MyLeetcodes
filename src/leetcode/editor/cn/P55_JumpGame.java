/******************************* Java：跳跃游戏 *******************************/
// 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
//
// 示例 1： 
//
// 输入：nums = [2,3,1,1,4]
// 输出：true
// 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 输入：nums = [3,2,1,0,4]
// 输出：false
// 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 
// 👍 1188 👎 0


/******************************* 题目思路 *******************************/
// 题目要求能否到达最后一个元素的位置，而每一次加入元素能到达的位置又取决于上一次能到达的位置，动态规划！
//
// 令 maxlocate(i) 为遍历到 num[i] 时最远能到达的位置，maxlocalte(i+1) = max( maxlocate(i), i+num[i] )
// 最终，如果 maxlocate(n) >= n-1 则说明能到达最后一个下标。
//
// 注意：maxlocate(i) 其实我们只需要最后一个 maxlocate(n) 即可，不需要记录前面的元素值，所以只需要维护一个变量的值即可，
//      也不需要用递归实现。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P55_JumpGame {
    public static void main(String[] args) {
        Solution solution = new P55_JumpGame().new Solution();
        // TO TEST
        int[] nums = {2,3,1,1,4};
        boolean answer = solution.canJump(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canJump(int[] nums) {
            int maxlocate = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i <= maxlocate) {        // 注意：一定不要漏掉这个判断条件
                    maxlocate = Math.max(maxlocate, i + nums[i]);
                    if (maxlocate >= nums.length - 1) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)
// 空间复杂度：O(1)