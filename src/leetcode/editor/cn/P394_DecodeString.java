/******************************* Java：字符串解码 *******************************/
// 给定一个经过编码的字符串，返回它解码后的字符串。
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 
//
// 示例 1： 
//
// 输入：s = "3[a]2[bc]"
// 输出："aaabcbc"
// 
//
// 示例 2： 
//
// 输入：s = "3[a2[c]]"
// 输出："accaccacc"
// 
//
// 示例 3： 
//
// 输入：s = "2[abc]3[cd]ef"
// 输出："abcabccdcdcdef"
// 
//
// 示例 4： 
//
// 输入：s = "abc3[cd]xyz"
// 输出："abccdcdcdxyz"
// 
// Related Topics 栈 递归 字符串 
// 👍 836 👎 0


/******************************* 题目思路 *******************************/
// 数字存放在数字栈，字符串存放在字符串栈，遇到右括号时候弹出一个数字栈，字母栈弹到左括号为止。就是逆波兰式那种题。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Stack;

class P394_DecodeString {
    public static void main(String[] args) {
        Solution solution = new P394_DecodeString().new Solution();
        // TO TEST
        String s = "3[a2[c]b]";
        String answer = solution.decodeString(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String decodeString(String s) {
            Stack<String> st_strpre = new Stack<>();
            Stack<Integer> st_num = new Stack<>();
            StringBuilder res = new StringBuilder("");

            int number = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                } else if (c == '[') {
                    st_num.push(number);
                    number = 0;
                    st_strpre.push(res.toString());
                    res.delete(0, res.length());
                } else if (c == ']') {
                    int k = st_num.pop();
                    StringBuilder str = new StringBuilder(st_strpre.pop());
                    for (int j = 0; j < k; j++) {
                        str.append(res);
                    }
                    res = str;
                } else {
                    res.append(c);
                }
            }

            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......