/******************************* Java：连续子数组的最大和 *******************************/
//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 数组 分治 动态规划 
// 👍 369 👎 0


/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn.finger_offer;

class P42_MaxConsecutiveSumInNums {
    public static void main(String[] args) {
        Solution solution = new P42_MaxConsecutiveSumInNums().new Solution();
        // TO TEST
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int answer = solution.maxSubArray(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int pre = 0;
            int maxsum = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                pre = Math.max(pre + nums[i], nums[i]);
                maxsum = Math.max(pre, maxsum);
            }
            return maxsum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......