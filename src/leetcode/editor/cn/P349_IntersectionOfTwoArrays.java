/******************************* Java：两个数组的交集 *******************************/
// 给定两个数组，编写一个函数来计算它们的交集。
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
// 输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// 输出：[9,4]
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 291 👎 0


/******************************* 题目思路 *******************************/
// 直接对排序好的数组，用双指针遍历即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P349_IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Solution solution = new P349_IntersectionOfTwoArrays().new Solution();
        // TO TEST
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] answer = solution.intersection(nums1, nums2);
        System.out.println(Arrays.toString(answer));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            // 排序
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            int[] result = new int[nums1.length + nums2.length];
            int i = 0, i1 = 0, i2 = 0;

            while (i1 < nums1.length && i2 < nums2.length) {
                if (nums1[i1] == nums2[i2]) {
                    if (i == 0 || nums1[i1] != result[i-1]) {  // 在结果数组里去重
                        result[i++] = nums1[i1];
                    }
                    i1 ++;
                    i2 ++;
                }
                else if (nums1[i1] < nums2[i2]) {
                    i1 ++;
                }
                else
                    i2 ++;
            }
            return Arrays.copyOfRange(result, 0, i);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......