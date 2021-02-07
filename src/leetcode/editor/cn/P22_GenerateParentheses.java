/******************************* Java：括号生成 *******************************/
// 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 输入：n = 3
// 输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 输入：n = 1
// 输出：["()"]
//
//
// 提示： 
//
// 1 <= n <= 8 
// 
// Related Topics 字符串 回溯算法 
// 👍 1550 👎 0


/******************************* 题目思路 *******************************/
// 方法一：回溯法
//      问题的关键在于，弄清什么时候能添加括号：
//      如果左括号数量不大于 n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
//
// 方法二：找规律模式
//      每一个括号序列可以用 (a)b 来表示，其中 a 与 b 分别是一个合法的括号序列（可以为空）;
//      要生成所有长度为 2 * n 的括号序列，我们定义一个函数 generate(n) 来返回所有可能的括号序列。那么在函数 generate(n) 的过程中：
//          - 我们需要枚举与第一个 ( 对应的 ) 的位置 2 * i + 1；
//          - 递归调用 generate(i) 即可计算 a 的所有可能性；
//          - 递归调用 generate(n - i - 1) 即可计算 b 的所有可能性；
//          - 遍历 a 与 b 的所有可能性并拼接，即可得到所有长度为 2 * n 的括号序列。
//      为了节省计算时间，我们在每次 generate(i) 函数返回之前，把返回值存储起来，下次再调用 generate(i) 时可以直接返回，不需要再递归计算。


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

class P22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22_GenerateParentheses().new Solution();
        // TO TEST
        int n = 3;
        List answer = solution.generateParenthesis(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrack(res, new StringBuilder(), 0, 0, n);
            return res;
        }

        private void backtrack(List<String> ans, StringBuilder cur, int left, int right, int max) {
            if (cur.length() == max * 2) {
                ans.add(cur.toString());
                return;
            }

            if (left < max) {
                cur.append('(');
                backtrack(ans, cur, left + 1, right, max);
                cur.deleteCharAt(cur.length() - 1);
            }
            if (left > right) {
                cur.append(')');
                backtrack(ans, cur, left, right + 1, max);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 复杂度分析依赖于理解 generateParenthesis(n) 中有多少个元素。
// 这个分析超出了本文的范畴，但事实证明这是第 n 个卡特兰数 1/(n+1)*(2n n) ，这是由 (4^n)/(n 根号n) 渐近界定的。
//
// 时间复杂度：O((4^n)/(n 根号n))，在回溯过程中，每个答案需要 O(n) 的时间复制到答案数组中。
// 空间复杂度：O(n)，除了答案数组之外，我们所需要的空间取决于递归栈的深度，每一层递归函数需要 O(1) 的空间，最多递归 2n 层
