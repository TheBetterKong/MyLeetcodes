/******************************* Java：整理字符串 *******************************/
// 给你一个由大小写英文字母组成的字符串 s 。
//
// 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件: 
//
// 
// 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。 
// 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。 
// 
//
// 请你将字符串整理好，每次你都可以从字符串中选出满足上述条件的 两个相邻 字符并删除，直到字符串整理好为止。 
//
// 请返回整理好的 字符串 。题目保证在给出的约束条件下，测试样例对应的答案是唯一的。 
//
// 注意：空字符串也属于整理好的字符串，尽管其中没有任何字符。 
//
// 
//
// 示例 1： 
//
// 
// 输入：s = "leEeetcode"
// 输出："leetcode"
// 解释：无论你第一次选的是 i = 1 还是 i = 2，都会使 "leEeetcode" 缩减为 "leetcode" 。
// 
//
// 示例 2： 
//
// 
// 输入：s = "abBAcC"
// 输出：""
// 解释：存在多种不同情况，但所有的情况都会导致相同的结果。例如：
// "abBAcC" --> "aAcC" --> "cC" --> ""
// "abBAcC" --> "abBA" --> "aA" --> ""
// 
//
// 示例 3： 
//
// 
// 输入：s = "s"
// 输出："s"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含小写和大写英文字母 
// 
// Related Topics 栈 字符串 
// 👍 15 👎 0


/******************************* 题目思路 *******************************/
// 难点在于怎样判断相邻两哥字符互为大小写：最简单的方式就是利用 ascii：c1 ^ c2 ==32

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1544_MakeTheStringGreat {
    public static void main(String[] args) {
        Solution solution = new P1544_MakeTheStringGreat().new Solution();
        // TO TEST
        String s = "leEeetcode";
        String answer = solution.makeGood(s);
        System.out.println(answer);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String makeGood(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (res.length() > 0 && Character.toLowerCase(res.charAt(res.length()-1)) == Character.toLowerCase(ch) && res.charAt(res.length()-1) != ch) {
                res.deleteCharAt(res.length()-1);
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(n)