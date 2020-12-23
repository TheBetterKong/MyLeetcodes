/******************************* Java：最长回文子串 *******************************/
// 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1： 
//
// 输入: "babad"
// 输出: "bab"
// 注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
// 输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 3028 👎 0


/******************************* 题目思路 *******************************/
// 方法一：动态规划
//      核心是找状态转移方程：
//          用 P(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串是否为回文串；
//          则，状态转移方程为：P(i, j) = P(i+1, j−1) ∧ (Si == Sj)
//      边界条件：
//          P(i, i) = true
//          P(i, i+1) = (Si == Si+1)
//      时间复杂度 O(n^2)
//      空间复杂度 O(n^2)
//
// （下面参考官方题解：）
// 方法二：中心扩展算法
//      状态转移链：P(i,j) ← P(i+1,j−1) ← P(i+2,j−2) ← ⋯ ← 某一边界情况
//      仔细观察方法一的上述状态转移链，会发现很大一部分的时间和空间复杂度都浪费在了不必要的计算上，
//      例如：如果 P(i+1,j−1) = false，那么他后面所有的扩展肯定都是 false，为了避免这种浪费：
//      我们可以：
//          边界情况即为子串长度为 1 或 2 的情况。
//          枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。
//              如果两边的字母相同，我们就可以继续扩展：例如从 P(i+1,j−1) 扩展到 P(i,j)；
//              如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
//      这种方法的本质：
//          枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
//          我们对所有的长度求出最大值，即可得到最终的答案。
//      代码：
//        class Solution {
//            public String longestPalindrome(String s) {
//                if (s == null || s.length() < 1) {
//                    return "";
//                }
//                int start = 0, end = 0;
//                for (int i = 0; i < s.length(); i++) {
//                    int len1 = expandAroundCenter(s, i, i);
//                    int len2 = expandAroundCenter(s, i, i + 1);
//                    int len = Math.max(len1, len2);
//                    if (len > end - start) {
//                        start = i - (len - 1) / 2;
//                        end = i + len / 2;
//                    }
//                }
//                return s.substring(start, end + 1);
//            }
//
//            public int expandAroundCenter(String s, int left, int right) {
//                while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
//                    --left;
//                    ++right;
//                }
//                return right - left - 1;
//            }
//        }
//      时间复杂度：O(n^2)
//      空间复杂度：O(1)
//
// 方法三：Manacher 算法
//      主要就是利用回文串的对称性，来跳过中心扩展算法中部分字符的扩展；
//      详情见后序的 blog 发布；
//
//
//



/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5_LongestPalindromicSubstring().new Solution();
        // TO TEST
        String s = "cbbd";
        String answer = solution.longestPalindrome(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            int lenth = s.length();
            boolean[][] dp = new boolean[lenth][lenth];
            String res = "";    // 用来实时更新保存最长的回文子串

            for (int len = 0; len < lenth; len ++) {  // 外层循环控制每轮 dp 的字符串的长度
                for (int i = 0; i + len < lenth; i ++) {    // 对 dp 数组遍历
                    if (len == 0)
                        dp[i][i + len] = true;
                    else if (len == 1)
                        dp[i][i + len] = (s.charAt(i) == s.charAt(i + len));
                    else {
                        dp[i][i + len] = (s.charAt(i) == s.charAt(i + len) && dp[i + 1][i + len - 1]);
                    }
                    if (dp[i][i + len] && len + 1 > res.length()) {
                        res = s.substring(i, i + len + 1);
                    }
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......