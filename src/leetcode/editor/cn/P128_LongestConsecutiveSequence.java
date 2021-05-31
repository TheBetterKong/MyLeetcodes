/******************************* Java：最长连续序列 *******************************/
// 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
//
//
// 示例 1： 
//
// 输入：nums = [100,4,200,1,3,2]
// 输出：4
// 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
//
// 示例 2： 
//
// 输入：nums = [0,3,7,2,5,8,4,6,0,1]
// 输出：9
// 
//
// 提示： 
//
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 
// 👍 782 👎 0


/******************************* 题目思路 *******************************/
// 最暴力的方法就是：
//      遍历每一个元素，考虑以它为起点，不断尝试 x+1, x+2, ... 是否存在，假设最长匹配为 x+y，那么以 x 为起点的最长匹配为 y+1；
//      在查找某元素是否存在时，利用 hash 查找，将时间复杂度优化为 O(1)
// 此时，时间复杂度为 O(n^2）
//
// 优化的思路是：去掉暴力查找中重复的查找
//      假设从 x 开始，找到的最长匹配为 x+y，那么下一次如果从 x+1 开始，那么 x+1 到 x+y 都为重复查找。
//      我们应该从下一个 >x+y 的元素开始查找，但问题是 hashset 里元素无序，我们并不知道这个元素是多少？
//          思路一：元素存 hashset 时，将 val 作为 key 存，这样 hashset 的大小为 219，从头到尾遍历一次，需要 219 次遍历；
//          思路二：遍历到元素 i 时，如果 i-1 在 hashset 里，说明可以跳过。这样最坏情况下，需要 219 次遍历

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new P128_LongestConsecutiveSequence().new Solution();
        // TO TEST
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int answer = solution.longestConsecutive(nums);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            // 利用集合去重
            for (int num : nums) {
                set.add(num);
            }
            // 遍历集合
            int longest = 0;
            for (int num : set) {
                if (!set.contains(num - 1)) {
                    int cur = num;
                    int curlongest = 1;

                    while (set.contains(cur + 1)) {
                        cur++;
                        curlongest++;
                    }

                    longest = Math.max(longest, curlongest);
                }
            }

            return longest;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，一次遍历 nums 里的所有数即可
// 空间复杂度：O(n)，hashset 的大小