/******************************* Java：颜色分类 *******************************/
// 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
//
// 示例 1： 
//
// 输入：nums = [2,0,2,1,1,0]
// 输出：[0,0,1,1,2,2]
//
// 示例 2： 
//
// 输入：nums = [2,0,1]
// 输出：[0,1,2]
//
// 示例 3： 
//
// 输入：nums = [0]
// 输出：[0]
//
// 示例 4： 
//
// 输入：nums = [1]
// 输出：[1]
// 
//
// 提示： 
//
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
//
// 进阶： 
//
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 排序 数组 双指针 
// 👍 889 👎 0


/******************************* 题目思路 *******************************/
// 按照题目要求，我们直接遍历数组，同时维护两个指针 left 和 right，分别指向当前以及换好位置的 0 的最右端，2 的最左端
// 接下来，遍历到 0 就将该数与 left 所指位置数互换，遍历到 2 就与 right 所指位置数互换
// 有一个细节需要注意：
//    left -> i -> right 这三个指针之间的数只能为 1，因此从后往前换数的时候，如果换过来的数不为 1，需要回退 i 指针

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P75_SortColors {
    public static void main(String[] args) {
        Solution solution = new P75_SortColors().new Solution();
        // TO TEST
        int[] nums = {2,0,2,1,1,0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void sortColors(int[] nums) {
            int lenth = nums.length;
            if (lenth < 2) return;

            int left = 0, right = lenth - 1;
            for (int i = 0; i <= right; i++) {
                if (nums[i] == 0) {
                    nums[i] = nums[left];
                    nums[left] = 0;
                    left++;
                    // 指针 i 是比 left 快的，因此 i 与 left 之间一定不存在 0 或 2
                }
                if (nums[i] == 2) {
                    nums[i] = nums[right];
                    nums[right] = 2;
                    right--;
                    // 因为不知道后面换过来的数是多少，如果是 0 或者 2 的话，需要回退再判断
                    if (nums[i] != 1) {
                        i--;
                    }
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)
// 空间复杂度：O(1)