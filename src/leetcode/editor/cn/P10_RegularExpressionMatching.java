/******************************* Java：正则表达式匹配 *******************************/
// 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。
// 
//
// 示例 1： 
//
// 
// 输入：s = "aa" p = "a"
// 输出：false
// 解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
// 输入：s = "aa" p = "a*"
// 输出：true
// 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
// 输入：s = "ab" p = ".*"
// 输出：true
// 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4： 
//
// 
// 输入：s = "aab" p = "c*a*b"
// 输出：true
// 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5： 
//
// 
// 输入：s = "mississippi" p = "mis*is*p*."
// 输出：false
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 20 
// 0 <= p.length <= 30 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 字符串 动态规划 回溯算法 
// 👍 1829 👎 0


/******************************* 题目思路 *******************************/
// 按照提示，本题也是一个动态规划的题目
// 第一步：找状态点
//    s 为匹配串，p 为模式串
//    那么可以用 f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配。
//    （注意：这里的 i 和 j 代表的是第 i 个字符（s[i-1]）和第 j 个字符（p[j-1]），不是位置 i 和 j，
//           f[0][0] 代表的是空串，而不是第一个字符）
// 第二步：捋清状态转移的关系，写出状态转移方程
//    本题，明显应该分情况进行考虑。那么，我们考虑 p 的第 j 个字符的匹配情况：
//      （1）如果 p 的第 j 个字符是 ”小写字母“：
//           此时，s 中的第 i 个字符必须是相同的小写字母，也就有：
//           f[i][j] = f[i-1][j-1]，s[i] = p[j]
//                   = false      ，s[i] ≠ p[j]
//      （2）如果 p 的第 j 个字符是 ”*“：
//           那就可以对 p 的第 j-1 个字符进行任意次数的匹配：
//              匹配 0 次时：f[i][j] = f[i][j-2]，注意 p[j] = *，应参与匹配的是 p[j-1]
//              匹配 1 次时：f[i][j] = f[i-1][j-2]，此时 s[i] = p[j−1]
//              匹配 2 次时：f[i][j] = f[i-2][j-2]，此时 s[i−1] = s[i] = p[j−1]
//              匹配 3 次时：f[i][j] = f[i-3][j-2]，此时 s[i-2] = s[i−1] = s[i] = p[j−1]
//              ......
//           上述关系，咋眼一看，很难写出明显的转移关系，但是仔细分析，其实也就是两种情况：
//              ① s 的末尾字符和 p 的第 j-1 个字符匹配，则将该字符扔掉，对 s 的下一个字符继续进行匹配
//              ② s 的末尾字符和 p 的第 j-1 个字符不匹配，则将该匹配模式扔掉，对 p 的下一个字符进行匹配
//           这样就可以总结出状态转移方程：
//           f[i][j] = f[i-1][j] or f[i][j-2]，s[i] = p[j-1]
//                   = f[i][j-2]             ，s[i] ≠ p[j-1]
//       （3）如果 p 的第 j 个字符是 “.”：
//            此时是一定会匹配成功的；
//    总结上述，最终的状态转移方程就是：
//          f[i][j] =   if (p[j] ≠ '*'):  f[i-1][j-1],            if matches(s[i], p[j])
//                                        false      ,            otherwise
//                  =   otherwise      :  f[i-1][j] or f[i][j-2]，if matches(s[i], p[j-1])
//                                        f[i][j-2]             ，otherwise
//          其中，matches 是用来判断两个字符是否匹配的辅助函数（只有当 y 是 “.” 或 x 和 y 相同时，才匹配）

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new P10_RegularExpressionMatching().new Solution();
        // TO TEST
        String s = "aab";
        String p = "c*a*b";

        boolean answer = solution.isMatch(s, p);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            int slenth = s.length();
            int plenth = p.length();

            boolean[][] f = new boolean[slenth + 1][plenth + 1];
            f[0][0] = true; // 两字符串为空字符串，肯定是可匹配的
            for (int i = 0; i <= slenth; i++) { // s 从没有字符开始
                for (int j = 1; j <= plenth; j++) {  // p 从第一个字符开始
                    if (p.charAt(j - 1) == '*') {
                        if (matches(s, p, i, j-1)) {
                            f[i][j] = f [i][j - 2] || f[i - 1][j];
                        } else {
                            f[i][j] = f [i][j - 2];
                        }
                    } else {
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i-1][j-1];
                        } else {
                            f[i][j] = false;
                        }
                    }
                }
            }

            return f[slenth][plenth];
        }

        private boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(mn)，m 和 n 分别为两个字符串的长度，这是因为需要计算出所有位置的状态
// 空间复杂度：O(mn)，要存储所有的状态空间