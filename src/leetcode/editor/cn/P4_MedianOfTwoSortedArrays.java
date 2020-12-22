/******************************* Java：寻找两个正序数组的中位数 *******************************/
// 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
// 输出：2.00000
// 解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
// 输出：2.50000
// 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
// 输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
// 输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
// 输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3529 👎 0


/******************************* 题目思路 *******************************/
// 最简单的办法是直接使用双指针遍历查找，找到正确的中位数位置即可，但是这样的时间复杂度 O(m+n)，会超时
// 下面是官方的两种思路：
// 方法一：二分查找
//      问题转换：
//          若 m+n 为奇数，则中位数为两数组合并后，编号为 (m+n)/2 的元素；
//          若 m+n 为奇数，则中位数为两数组合并后，编号为 (m+n)/2 和 编号为 (m+n)/2+1 的元素的均值；
//          所以问题就变成了，求合并数组中，第 k 小的数（注意 k 和编号的关系）；
//      二分查找的思想：
//          三种查找情况：
//              ① 如果 A[k/2−1] < B[k/2−1]，则比 A[k/2−1] 小的数最多只有 A 的前 k/2−1 个数和 B 的前 k/2−1 个数，
//                 即比 A[k/2−1] 小的数最多只有 k−2 个，因此 A[k/2−1] 不可能是第 k 个数，A[0] 到 A[k/2−1] 也都不可能是第 k 个数，可以全部排除。
//              ② 如果 A[k/2−1] > B[k/2−1]，则可以排除 B[0] 到 B[k/2−1]。
//              ③ 如果 A[k/2−1] = B[k/2−1]，则可以归入第一种情况处理。
//          比较 A[k/2−1] 和 B[k/2−1] 之后，可以排除 k/2 个不可能是第 k 小的数，查找范围缩小了一半。同时，我们将在排除后的新数组上继续进行二分查找，并且根据我们排除数的个数，减少 k 的值；
//          三种情况需要特殊处理：
//              ① 如果 A[k/2−1] 或者 B[k/2−1] 越界，可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。
//              ② 如果一个数组为空，说明该数组中的所有元素都被排除，可以直接返回另一个数组中第 k 小的元素。
//              ③ 如果 k=1，返回两个数组首元素的最小值；
//      时间复杂度：O(log(m+n))
//      空间复杂度：O(1)
// 方法二：划分数组
//      问题转换：
//          中位数的作用，将集合分为两个长度相等的子集，并且一个子集的元素总是小于另一个子集；
//          对数组 A 从位置 i 处 分为 A[0, i-1] 和 A[i, ...] 两部分，对数组 B 从位置 j 处 分为 A[0, j-1] 和 A[j, ...] 两部分；
//          接下来就是要保证：i+j = m−i+n−j（当 m+n 为偶数）或 i+j = m−i+n−j+1（当 m+n 为奇数）。等号左侧为前一部分的元素个数，等号右侧为后一部分的元素个数。
//          将上述公式变型，就可以统一为：i+j = (m+n+1)/2
//      保证的两个条件：
//          ① 0 ≤ i ≤ m，0 ≤ j ≤ n。如果我们规定 A 的长度小于等于 B 的长度，即 m≤n。这样对于任意的 i∈[0,m]，都有 j = (m+n+1)/2 - i ∈[0,n]，
//             那么我们在 [0,m] 的范围内枚举 i 并得到 j，就不需要额外的性质了。
//          ② B[j−1] ≤ A[i] 以及 A[i−1] ≤ B[j]，即前一部分的最大值小于等于后一部分的最小值。
//      现在的问题就相当于是：
//          在 [0,m] 中找到最大的 i，使得：j = (m+n+1)/2 - i。
//          因此我们可以对 i 在 [0,m] 的区间上进行二分搜索，找到最大的满足 A[i−1] ≤ B[j] 的 i 值，就得到了划分的方法。
//          此时，划分前一部分元素中的最大值，以及划分后一部分元素中的最小值，才可能作为就是这两个数组的中位数。
//      代码如下：
//        class Solution {
//            public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//                if (nums1.length > nums2.length) {
//                    return findMedianSortedArrays(nums2, nums1);
//                }
//
//                int m = nums1.length;
//                int n = nums2.length;
//                int left = 0, right = m;
//                // median1：前一部分的最大值
//                // median2：后一部分的最小值
//                int median1 = 0, median2 = 0;
//
//                while (left <= right) {
//                    // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
//                    // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
//                    int i = (left + right) / 2;
//                    int j = (m + n + 1) / 2 - i;
//
//                    // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
//                    int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
//                    int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
//                    int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
//                    int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);
//
//                    if (nums_im1 <= nums_j) {
//                        median1 = Math.max(nums_im1, nums_jm1);
//                        median2 = Math.min(nums_i, nums_j);
//                        left = i + 1;
//                    } else {
//                        right = i - 1;
//                    }
//                }
//
//                return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
//            }
//        }
//      时间复杂度：O(logmin(m,n)))
//      空间复杂度：O(1)








/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4_MedianOfTwoSortedArrays().new Solution();
        // TO TEST
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double answer = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length, len2 = nums2.length;
            int len = len1 + len2;
            double midian;
            if (len % 2 == 1) {
                midian = getKthElement(nums1, nums2, len / 2 + 1); // 注意元素索引与第 k 小的对应关系
            } else {
                midian = (getKthElement(nums1, nums2, len / 2) + getKthElement(nums1, nums2, len / 2 + 1)) / 2.0;
            }
            return midian;
        }


        private int getKthElement(int[] nums1, int[] nums2, int k) {
            int len1 = nums1.length, len2 = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                // 判断边界
                if (index1 == len1)     // 直接返回另一个数组中第 k 小的元素。
                    return nums2[index2 + k - 1];
                if (index2 == len2)
                    return nums1[index1 + k - 1];
                if (k == 1)
                    return Math.min(nums1[index1], nums2[index2]);

                // 正常情况：
                int newIndex1 = Math.min(index1 + k / 2, len1) - 1; // 更新用来比较的元素索引，并防止越界
                int newIndex2 = Math.min(index2 + k / 2, len2) - 1;
                if (nums1[newIndex1] <= nums2[newIndex2]) {
                    k -= (newIndex1 - index1 + 1);  // 根据索引求被排除的元素数来更新 k 值，注意不要直接减 k/2
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 见问题分析部分