/******************************* Java：两个数组的交集 II *******************************/
// 给定两个数组，编写一个函数来计算它们的交集。
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
// 输出：[2,2]
// 
//
// 示例 2: 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// 输出：[4,9]
//
// 
//
// 说明： 
//
// 
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。 
// 我们可以不考虑输出结果的顺序。 
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
// Related Topics 排序 哈希表 双指针 二分查找 
// 👍 415 👎 0


/******************************* 题目思路 *******************************/
// 其实就是在 350 的基础上，更加简单，还少了一个去重的操作。
// 但是我们知道，如果数组的元素过多，无法一次加载到内存时，排序操作会变得很慢。有没有更加改进的办法呢？
// 这里可以采用 哈希表，先遍历较小的数组，存储各个字符及其出现的次数。然后再遍历较长的数组，并判断求交。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class P350_IntersectionOfTwoArraysIi {
    public static void main(String[] args) {
        Solution solution = new P350_IntersectionOfTwoArraysIi().new Solution();
        // TO TEST
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        int[] answer = solution.intersect(nums1, nums2);
        System.out.println(Arrays.toString(answer));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // 保证所求的 nums1 <= nunms2
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer,Integer> map = new HashMap<Integer, Integer>();

        // 遍历较小的数组，并存入 哈希表
        for (int n1 : nums1) {
            int count = map.getOrDefault(n1, 0) + 1;
            map.put(n1, count);
        }

        int[] result = new int[nums1.length];
        int i = 0;

        // 遍历较大的数组，求交集
        for (int n2 : nums2) {
            int count = map.getOrDefault(n2, 0);
            if (count > 0) {
                result[i++] = n2;
                count --;
                if (count > 0) {
                    map.put(n2, count);
                } else {
                    map.remove(n2);
                }
            }
        }

        return Arrays.copyOfRange(result, 0, i);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度，两次遍历 O(m+n)，m，n 分别为 nums1 和 nums2 的长度
// 空间复杂度，O(min(m,n))