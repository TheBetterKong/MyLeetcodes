/******************************* Java：有效的字母异位词 *******************************/
// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
// 输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
// 输出: false
//
// 说明: 
// 你可以假设字符串只包含小写字母。
//
// 进阶: 
// 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表 
// 👍 275 👎 0


/******************************* 题目思路 *******************************/
// 直接将两个字符串排序，然后对比排序后的两个字符串是否相等即可。
// 或者计数两个字符串里各个字符出现的次数是否相等；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P242_ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new P242_ValidAnagram().new Solution();
        // TO TEST
        String s = "rat";
        String t = "cat";
        boolean answer = solution.isAnagram(s, t);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length())
                return false;
            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度，主要来源于两个字符串的排序，O(nlogn)
// 空间复杂度，java 字符串的不变性，在 toCharArray 时，会占用 O(n) 的辅助空间