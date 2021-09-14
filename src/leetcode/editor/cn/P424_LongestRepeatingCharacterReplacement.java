/******************************* Java：替换后的最长重复字符 *******************************/
//给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
// 
//
// 注意：字符串长度 和 k 不会超过 104。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ABAB", k = 2
//输出：4
//解释：用两个'A'替换为两个'B',反之亦然。
// 
//
// 示例 2： 
//
// 
//输入：s = "AABABBA", k = 1
//输出：4
//解释：
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 458 👎 0


/******************************* 题目思路 *******************************/
// 主要思想： 滑动窗口
// 难点：    不能简单的一直用右边或者左边的字符进行替换，实际中应该是 “窗口内出现次数少的字符被替换”

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P424_LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        Solution solution = new P424_LongestRepeatingCharacterReplacement().new Solution();
        // TO TEST
        String s = "AAABBBC";
        int k = 3;
        int answer = solution.characterReplacement(s, k);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int characterReplacement(String s, int k) {
            int[] num = new int[26];    //  保存窗口内每一个字符出现的次数
            int n = s.length();
            int maxn = 0;   // 窗口内出现次数最多的字符的次数
            int left = 0, right = 0;
            while (right < n) {
                num[s.charAt(right) - 'A']++;
                maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
                if (right - left + 1 - maxn > k) {
                    num[s.charAt(left) - 'A']--;
                    left++;
                }
                right++;
            }
            return right - left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)，遍历字符串一次
// 空间复杂度：O(26)，nums 数组的 size