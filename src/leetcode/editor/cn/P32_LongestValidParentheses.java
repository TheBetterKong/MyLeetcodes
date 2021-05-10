/******************************* Java：最长有效括号 *******************************/
// 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
// 
//
// 示例 1： 
//
// 输入：s = "(()"
// 输出：2
// 解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 输入：s = ")()())"
// 输出：4
// 解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 输入：s = ""
// 输出：0
// 
//
//
// 提示： 
//
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
//
// Related Topics 字符串 动态规划 
// 👍 1292 👎 0


/******************************* 题目思路 *******************************/
// 括号匹配问题最常用的数据结构就是 “栈”。
//
// 这个问题和我们之前遇到的括号匹配问题很相似，以往我们是简单的判断字符串括号能否匹配，
// 所以我们简单的遇到左括号入栈，遇到右括号出栈，中途栈中没有元素弹出时，说明匹配失败。
//
// 但回到此问题，是要求最长（连续）匹配的子串的长度：
//
// 一开始，我的思路就是，在上面程序的基础上，加入匹配失败判断，然后重新开始计数长度，
// 什么时候失败？重新从哪开始计数？这样就有两个要考虑的问题。
// 实际编写中，会发现很容易忽视边界条件、特殊情况，这样分析起来比较杂、乱。
//
// 再看看上面遇到的两个问题，我们总结一下，其实就是需要保留 “最后一个没有被匹配到得右括号的下标”。
// 同样两个问题：① 怎样算这个下标？ ② 怎样用这个下标？
// 主要思路还是和之前一样，利用栈
//      对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
//      对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
//          如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
//          如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
// 注意一个特殊情况：初始时，栈空，第一个字符为 '('，将其入栈，那么在它之前就不存了没有被匹配的右括号的下标，这是不被允许的，
// 所以需要做一个处理：初始时，入栈一个值 -1


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

class P32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P32_LongestValidParentheses().new Solution();
        // TO TEST
        String s = "()(()";
        int answer = solution.longestValidParentheses(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            int maxlen = 0;
            int len = s.length();
            Deque<Integer> stack = new LinkedList<Integer>();
            stack.push(-1);

            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        maxlen = Math.max(maxlen, i - stack.peek());
                        //“最后一个没有被匹配到得右括号的下标” 它是始终往后增的，所以这里不能用 pop，否则栈顶元素就变成了上一个未被匹配的右括号下标
                    }
                }
            }

            return maxlen;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
//时间复杂度： O(n)，n 是给定字符串的长度。我们只需要遍历字符串一次即可。
//空间复杂度： O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n)