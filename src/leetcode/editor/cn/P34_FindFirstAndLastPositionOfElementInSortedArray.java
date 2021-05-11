/******************************* Java：在排序数组中查找元素的第一个和最后一个位置 *******************************/
// 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶：
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 输入：nums = [5,7,7,8,8,10], target = 8
// 输出：[3,4]
//
// 示例 2： 
//
// 输入：nums = [5,7,7,8,8,10], target = 6
// 输出：[-1,-1]
//
// 示例 3： 
//
// 输入：nums = [], target = 0
// 输出：[-1,-1]
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 995 👎 0


/******************************* 题目思路 *******************************/
// 有序静态数组的查找问题，要求时间复杂度 O(log n)，那最先想到的办法就是二分查找。
// 但有点不同的是，数组里元素不一定唯一，需要返回始终位置，所以需要考虑的是二分里的边界情况。
//
// 其实处理起来也方便，两次调用二分查找，至于顶层代码是两次调用都查找 target 还是查 target-1 还是 target+1 则
// 需要根据你二分查找的具体实现来看。
// 比如我在实现二分查找时，控制其查找最靠前的，所以我在底层调用时，就查找 target 以及 target+1；
//
// 所以，本题主要考察的还是二分查找的实现细节！

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new P34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
        int[] nums = {1};
        int target = 1;
        int[] answer = solution.searchRange(nums, target);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int leftindex = binsearch(nums, target);
            int rightindex = binsearch(nums, target+1);
            if (leftindex == nums.length || nums[leftindex] != target) {
                return new int[]{-1, -1};
            }
            return new int[]{leftindex, rightindex-1};
        }

        private int binsearch(int[] nums, int aim) {
            int lenth = nums.length;
            int low = 0, high = lenth, mid = 0;
            while (low < high) {
                mid = (low + high) / 2;
                if (nums[mid] >= aim) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(log n)，两次调用二分查找
// 空间复杂度:O(1)