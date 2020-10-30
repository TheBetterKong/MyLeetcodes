/******************************* Java：有效的括号 *******************************/
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
// 输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
// 输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
// 输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
// 输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
// 输出: true
// Related Topics 栈 字符串 
// 👍 1886 👎 0


/******************************* 题目思路 *******************************/
// 括号匹配是很经典的栈的应用，本题就是需要注意时刻判断栈是否为空

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.Stack;

class P20_ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P20_ValidParentheses().new Solution();
        // TO TEST
        String s = new String("{[]}");
        boolean answer = solution.isValid(s);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        if(s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<Character>();
        for(char c : s.toCharArray()){
            if(c == '{')
                stack.push('}');
            else if(c == '[')
                stack.push(']');
            else if(c == '(')
                stack.push(')');
            else if(stack.empty() || c != stack.pop())
                return false;
        }
        if(stack.empty())
            return true;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(m) ，m 是字符串中 左括号 的数量