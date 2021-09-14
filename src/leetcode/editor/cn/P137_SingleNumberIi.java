/******************************* Java：只出现一次的数字 II *******************************/
//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,2,3,2]
// 输出：3
// 
//
// 示例 2： 
//
// 输入：nums = [0,1,0,1,0,1,99]
// 输出：99
//
//
// 提示： 
//
// 1 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 数组 
// 👍 686 👎 0


/******************************* 题目思路 *******************************/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P137_SingleNumberIi {
    public static void main(String[] args) {
        Solution solution = new P137_SingleNumberIi().new Solution();
        // TO TEST
        int[] nums = {5,4,1,1,5,1,5};
        int answer = solution.singleNumber(nums);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int a_next = ~a & b & num | a & ~b & ~num;
            int b_next = ~a & (b ^ num);
            a = a_next;
            b = b_next;
        }
        return b;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......