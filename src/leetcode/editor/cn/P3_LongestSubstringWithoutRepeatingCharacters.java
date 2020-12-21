/******************************* Java：无重复字符的最长子串 *******************************/
// 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 
//
// 示例 1: 
//
// 
// 输入: s = "abcabcbb"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
// 输入: s = "bbbbb"
// 输出: 1
// 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
// 输入: s = "pwwkew"
// 输出: 3
// 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//    请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
// 输入: s = ""
// 输出: 0
// 
//
// 
//
// 提示： 
//
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4730 👎 0


/******************************* 题目思路 *******************************/
// 两个关键步骤：
//  1. 滑动窗口的思想：利用双指针，模拟滑动窗口左右指针；
//  2. 判断窗口内是否有重复元素：利用 hash 表实现，左指针移动一个，就往 hash 表移除一个元素，右指针移动一个，就往 hash 加入一个元素；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
        // TO TEST
        String s = "pwwkew";
        int answer = solution.lengthOfLongestSubstring(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<Character>();
            int lenth = s.length();
            int left = 0, right = -1;      // 左右指针，注意初始值设置
            int res = 0;        // 长度

            while (left < lenth) {

                // 滑动窗口右指针（扩大窗口）
                while (right + 1 < lenth && !set.contains(s.charAt(right + 1))) {
                    set.add(s.charAt(right + 1));
                    right ++;
                }

                // 窗口扩至最大后，更新最长子串的长度
                res = Math.max(res, right - left + 1);

                // 滑动窗口左指针（窗口右移一格）
                set.remove(s.charAt(left));
                left ++;
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，n 是字符串的长度，虽然看似有双重循环，但是，左右指针实际最多只会遍历链表一次，它们都没有回退的情况出现
// 空间复杂度 O(K)，K 是字符集长度，默认的 ASCII 码，最多 128 个字符