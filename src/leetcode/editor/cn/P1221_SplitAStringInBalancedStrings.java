/******************************* Java：分割平衡字符串 *******************************/
// 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
//
// 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。 
//
// 返回可以通过分割得到的平衡字符串的最大数量。 
//
// 
//
// 示例 1： 
//
// 
// 输入：s = "RLRRLLRLRL"
// 输出：4
// 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
// 
//
// 示例 2： 
//
// 
// 输入：s = "RLLLLRRRLR"
// 输出：3
// 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
// 
//
// 示例 3： 
//
// 
// 输入：s = "LLLLRRRR"
// 输出：1
// 解释：s 只能保持原样 "LLLLRRRR".
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s[i] = 'L' 或 'R' 
// 分割得到的每个字符串都必须是平衡字符串。 
// 
// Related Topics 贪心算法 字符串 
// 👍 72 👎 0


/******************************* 题目思路 *******************************/
// 既然只要求出现次数相同，那么直接从前往后划分，中途记录两种字符出现的次数，只要相同就划分出来即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1221_SplitAStringInBalancedStrings {
    public static void main(String[] args) {
        Solution solution = new P1221_SplitAStringInBalancedStrings().new Solution();
        // TO TEST
        String s = "RLLLLRRRLR";
        int answer = solution.balancedStringSplit(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int balancedStringSplit(String s) {
            char[] sChar = s.toCharArray();
            // 中间过程中两个字符出现的次数
            int countL = 0;
            int countR = 0;
            // 最终能分割成的字符串的长度
            int length = 0;

            for (char ch : sChar) {
                if (ch == 'L')
                    countL ++;
                else if (ch == 'R')
                    countR ++;

                if (countL == countR && countL != 0) {
                    length ++;
                }
            }

            return length;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，n 为字符串的长度
// 空间复杂度 O(n)，主要来源于将字符串转化为数组，便于操作
