/******************************* Java：合并两个有序数组 *******************************/
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
// nums1 = [1,2,3,0,0,0], m = 3
// nums2 = [2,5,6],       n = 3
//
// 输出: [1,2,2,3,5,6]
// Related Topics 数组 双指针 
// 👍 630 👎 0


/******************************* 题目思路 *******************************/
// 数组归并类型的题目：
//      思路一：直接复制 nums2 至 nums1 末尾，然后再对 nums1 调用排序算法，时间复杂度：O(n)+O((n+m)log(n+m))；属于暴力解法
//      思路二：从头开始，将 nums2 中的数插入到 nums1 中的相应位置。这种方法每一次插入都可能会移动 nums1 中的大量元素，
//              若要避免元素移动，就只能开辟新数组。但是这样又会带来空间浪费；
//      思路三：进一步改进思路二。既然从头开始插入，会导致 nums1 中元素移动，而且 nums1 已经足够容纳合并后的元素。
//              那就利用双指针，从末尾开始，每一次直接将元素放入合理位置，这样也就不会带来空间浪费。并且时间复杂度仅为 O(n+m)

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P88_MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new P88_MergeSortedArray().new Solution();
        // TO TEST
        int []nums1 = {1,2,3,0,0,0};
        int m = 3;
        int []nums2 = {2,5,6};
        int n = 3;
        solution.merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // p1，p2，p 分别代表 nums1 nums2 已经合并后的 nums1 末尾处
            int p1 = m-1;
            int p2 = n-1;
            int p = m+n-1;
            while(p2 >= 0 && p1 >=0){
                if(nums2[p2] > nums1[p1]){
                    nums1[p] = nums2[p2];
                    p2--;
                }
                else {
                    nums1[p] = nums1[p1];
                    p1--;
                }
                p--;
            }
            System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度为 O(n+m)