/******************************* Java：反转字符串中的元音字母 *******************************/
// 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
//
// 
//
// 示例 1： 
//
// 输入："hello"
// 输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
// 输出："leotcede"
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 
// 👍 120 👎 0


/******************************* 题目思路 *******************************/
// 也是十分基础的题，主要是弄清元音字母有哪些：
//      yuan = {'a','e','i','o','u','A','E','I','O','U'}

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Arrays;

class P345_ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new P345_ReverseVowelsOfAString().new Solution();
        // TO TEST
        String s = "leetcode";
        String answer = solution.reverseVowels(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseVowels(String s) {
            if (s == null || s.length() == 0 || s.length() == 1) return s;

            int n = s.length();
            int left = 0, right = n-1;
            char []chars = s.toCharArray();

            while (left < right) {
                while (left <= n-1 && !isYuan(chars[left])) {
                    left++;
                }
                while (right >= 0 && !isYuan(chars[right])) {
                    right--;
                }
                if (left < right) {
                    char temp = chars[right];
                    chars[right] = chars[left];
                    chars[left] = temp;
                    left++;
                    right--;
                }
            }
            return new String(chars);
        }

        private boolean isYuan (char ch) {
            switch (ch) {
                case 'a','e','i','o','u','A','E','I','O','U':
                    return true;
                default:
                    return false;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......