/******************************* Java：下一个更大元素 I *******************************/
// 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个
// 比其大的值。
//
// nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。 
//
// 
//
// 示例 1: 
//
// 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
// 输出: [-1,3,-1]
// 解释:
//    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
//    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
//    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。 
//
// 示例 2: 
//
// 输入: nums1 = [2,4], nums2 = [1,2,3,4].
// 输出: [3,-1]
// 解释:
//    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
//    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
// 
//
// 
//
// 提示： 
//
// 
// nums1 和 nums2 中所有元素是唯一的。
// nums1 和 nums2 的数组大小都不超过1000。
// 
// Related Topics 栈 
// 👍 288 👎 0


/******************************* 题目思路 *******************************/
// 本题真的怎么看也不像一个简单题，看了官方题解，居然需要使用到 stack 和 map 两种数据结构。
// 主要思想是，根据 nums2 构造单调栈，同时随时将 nums2 里每个元素的下一个较大数存入 map 中：
//     首先入栈 num2[0]，
//     对于 nums2[1]：
//          如果 nums2[1] <= nums2[0]，直接 num2[1] 入栈；
//          如果 nums2[1] > nums2[0]，则 nums2[0] 的一个较大数为 nums2[1]，<nums2[0],nums2[1]> 存入 map，nums2[0] 出栈，nums2[1] 入栈
//     对于 nums2[2]：
//          如果 nums2[2] <= nums2[1]，还是直接 num2[2] 入栈即可；
//          如果 nums2[2] > nums2[1]，则栈里所有比 nums[2] 小的元素都找到了下一个较大数，存入 map，并出栈，然后 num2[2] 入栈；
//     ......
// 上面，维护的栈就是一个从栈底到栈顶单调不增的单调栈；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

class P496_NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new P496_NextGreaterElementI().new Solution();
        // TO TEST
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] answer = solution.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 初始化声明
            Stack<Integer> stack = new Stack<>();
            HashMap<Integer,Integer> map = new HashMap<>();
            int[] res = new int[nums1.length];

            // 遍历 nums2，构造 单调栈 和 map
            for (int i = 0; i < nums2.length; i++) {
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    map.put(stack.pop(), nums2[i]);
                }
                stack.push(nums2[i]);
            }
            while (!stack.empty()) {    // nums2 遍历完后，处理 stack 里剩余的元素，都是没有下一个更大元素
                map.put(stack.pop(), -1);
            }

            // 利用 map 返回 num1 每个元素的下一个更大元素
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(m+n)，其中 m 为 nums1 的长度，n 为 nums2 的长度，主要来源于两次遍历；
// 空间复杂度 O(n)，遍历 nums2 时，同时使用到了 stack 和 map；