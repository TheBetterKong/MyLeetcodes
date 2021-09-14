/******************************* Java：最长递增子序列 *******************************/
// 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
// 
//
// 示例 1： 
//
// 输入：nums = [10,9,2,5,3,7,101,18]
// 输出：4
// 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 输入：nums = [0,1,0,3,2,3]
// 输出：4
// 
//
// 示例 3： 
//
// 输入：nums = [7,7,7,7,7,7,7]
// 输出：1
// 
//
// 提示： 
//
// 1 <= nums.length <= 2500 
// -104 <= nums[i] <= 104 
// 
//
// 进阶： 
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？ 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
// Related Topics 数组 二分查找 动态规划 
// 👍 1743 👎 0


/******************************* 题目思路 *******************************/
// 关键的数据的结构：
//   （1）maxLength[n]：保存每一个 arr[i] 处最长递增子序列的长度
//   （2）ArrayList<Integer> result：类似当前最长递增子序列，用于新的 arr[i] 比其末尾元素大时，做回溯；result 的大小一定是最终解大小，但是其不是最终解；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

class P300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new P300_LongestIncreasingSubsequence().new Solution();
        // TO TEST
        int[] nums = {10, 9, 2, 5, 3, 1, 101, 18};
        int res = solution.lengthOfLIS(nums);
        System.out.println(res);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            ArrayList<Integer> result = new ArrayList<>();  // 当前最长递增子序列
            int[] maxLength = new int[n];       // 保存至当前位置处的最长递增子序列的长度

            for (int i = 0; i < n; i++) {
                if (result.size() == 0 || nums[i] > result.get(result.size() - 1)) {
                    result.add(nums[i]);
                    maxLength[i] = result.size();
                } else {
                    int back = searchBin(result, nums[i]);
                    result.set(back, nums[i]);
                    maxLength[i] = back + 1;
                }
            }

            // 用于回造 “最长递增子序列”
            int[] res = new int[result.size()];
            for (int i = nums.length - 1, j = result.size(); j > 0; i--) {
                if (maxLength[i] == j) {
                    res[--j] = nums[i];
                }
            }
            return res.length;
        }

        /**
         * 二分查找
         */
        private int searchBin(ArrayList<Integer> d, int target) {
            // 返回链表里，最左边的那个 val 大于等于 target 的索引
            if (d.isEmpty()) {
                return 0;
            }
            int len = d.size();
            int left = 0;
            int right = len - 1;
            int mid = 0;

            while (left <= right) {
                mid = (left + right) / 2;
                if (d.get(mid) == target) {
                    do {
                        mid--;
                    } while (mid >= 0 && d.get(mid) == target);
                    return mid + 1;
                } else if (d.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (d.get(mid) < target) {
                return mid + 1;
            } else return mid;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......