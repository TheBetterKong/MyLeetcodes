/******************************* Java：最大子序和 *******************************/
// 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
// 输出: 6
// 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 2453 👎 0


/******************************* 题目思路 *******************************/
// 本题主要求解思路是：动态规划
// 动态规划里，核心的思想就是问题拆分：
//      在本题中，我们可以假设已经求出至数组 nums[i] 位置连续子数组的最大和为 f(n)；
//      那么，在 i+1 位置连续子数组的最大和 f(n+1) = max ( f(n)+num[i+1] ，nums[i+1] ）；
// 本题也不需要我们求出这个连续子数组，因此只需要再使用一个变量 result 来时刻更新最大值即可；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P53_MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new P53_MaximumSubarray().new Solution();
        // TO TEST
        int []nums = {-2,1,-3,4,-1,2,1,-5,4};
        int answer = solution.maxSubArray(nums);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        int imax = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            imax = Math.max(nums[i], imax + nums[i]);
            result = Math.max(imax, result);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)