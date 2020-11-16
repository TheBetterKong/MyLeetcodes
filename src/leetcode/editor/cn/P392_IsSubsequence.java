/******************************* Java：判断子序列 *******************************/
// 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
// c"不是）。
//
// 示例 1: 
// s = "abc", t = "ahbgdc"
//
// 返回 true. 
//
// 示例 2: 
// s = "axc", t = "ahbgdc"
//
// 返回 false. 
//
// 后续挑战 : 
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
//
// 致谢: 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
// Related Topics 贪心算法 二分查找 动态规划 
// 👍 353 👎 0


/******************************* 题目思路 *******************************/
// 双指针解法（比较简单）：
//      直接设置两个指针 is 和 it 分别指向 s 和 t。
//      然后遍历这两个字符串，匹配成功就 is 和 it 同时后移一位，否则 it 后移一位；
// 采用动态规划方式（求出 t 的相关信息后，可以直接处理 s，解决了挑战问题）：
//      预处理出对于 t 的每一个位置，从该位置开始往后每一个字符第一次出现的位置：
//          1. 令 f[i][j] 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置；
//          2. 状态转移时，如果 t 中位置 i 的字符就是 j，那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，即 f[i][j]=f[i+1][j]；
//                  状态转移方程：
//                      f[i][j] = i        ，    t[i] = j
//                      f[i][j] = f[i+1][j]，    else
//          3. 方向进行动态规划，从后往前枚举 i；


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P392_IsSubsequence {
    public static void main(String[] args) {
        Solution solution = new P392_IsSubsequence().new Solution();
        // TO TEST
        String s = "axc", t = "ahbgdc";
        boolean answer = solution.isSubsequence(s, t);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSubsequence(String s, String t) {

            int slen = s.length();
            int tlen = t.length();

            /**
             * 动态规划，预处理 f[i][j] 的过程
             */
            int[][] f = new int[tlen + 1][26];  // 动态规划需要多处理一个位置； 26 的不同的字符；

            // 初始化 t 最后一个字符的下一个位置，便于递推项的进行
            for (int i = 0; i < 26; i++) {
                f[tlen][i] = tlen;  // 后面没了字符，设置为 tlen 超过 t 的长度，代表 t 里面没有出现过该字符；
            }

            // 开始遍历字符串 t，填充 f 数组
            for (int i = tlen-1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if (t.charAt(i) == j + 'a') {
                        f[i][j] = i;
                    }
                    else {
                        f[i][j] = f[i+1][j];
                    }
                }
            }

            /**
             * 利用 f[i][j] 开始判断 s 是否为 t 的子串
             */
            int idex = 0;   // 用于记录在 t 中记录到了的位置，相当于双指针方法中的 it；
            for (int i = 0; i < slen; i++) {
                if (f[idex][s.charAt(i) - 'a'] == tlen) {
                    return false;
                }
                idex = f[idex][s.charAt(i) - 'a'] + 1;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：
//      预处理：O(m) * 26，m 为 t 的长度
//      判断子序列：O(n)，n 为 s 的长度
// 空间复杂度：O(m) * 26