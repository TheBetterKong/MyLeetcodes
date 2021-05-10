/******************************* Java：搜索旋转排序数组 *******************************/
// 整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
// k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
// ,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [4,5,6,7,0,1,2], target = 0
// 输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [4,5,6,7,0,1,2], target = 3
// 输出：-1
//
//
// 示例 3： 
//
// 输入：nums = [1], target = 0
// 输出：-1
// 
//
// 
//
// 提示： 
//
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 
// 
//
// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1353 👎 0


/******************************* 题目思路 *******************************/
// 题目给出：数据保证 nums 在预先未知的某个下标上进行了旋转
// 那么最简单的思路：顺序遍历，但是这样时间复杂度为 O(n) 超时。
//
// 要求时间复杂度为 O(log n)，回顾查找算法，达到这个要求的：二分查找
// 本题和剑指 offer T6 有异曲同工之妙，详情可见：
// http://www.thebetterkong.cn/2020/04/05/DataStructure-Algorithm/Finger-offer67/

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import javax.swing.text.Style;

class P33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new P33_SearchInRotatedSortedArray().new Solution();
        // TO TEST
        int[] nums = {4,5,6,7,0,1,2};
        int target = 2;
        int answer = solution.search(nums, target);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int search(int[] nums, int target) {
            int length = nums.length;
            if (length == 0) return -1;

            int low = 0, high = length - 1, mid = 0;
            while (low <= high) {
                mid = (low + high) / 2;
                if (target == nums[mid]) return mid;
                if (nums[mid] > nums[high]) {   // 旋转点一定在 mid~high 之间，那么从 low~mid，数组一定递增
                    if (target >= nums[low] && target < nums[mid]) {
                        high = mid - 1;
                    }
                    else {
                        low = mid + 1;
                    }
                } else {                        // 旋转点一定在 low~mid 之间，那么从 mid~high，数组一定递增
                    if (target > nums[mid] && target <= nums[high]) {
                        low = mid + 1;
                    }
                    else {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(log n)，即二分查找的时间开销
// 空间复杂度：O(1)