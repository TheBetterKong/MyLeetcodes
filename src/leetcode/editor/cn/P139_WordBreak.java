/******************************* Java：单词拆分 *******************************/
// 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明： 
//
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
// 输出: true
// 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
// 输出: true
// 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//    注意你可以重复使用字典中的单词。
//
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
// 输出: false
// 
// Related Topics 动态规划 
// 👍 1009 👎 0


/******************************* 题目思路 *******************************/
// 构造 dp 数组，dp[i] 表示字符串 s 的前 i 个字符组成的字符串 s[0, ..., i-1] 是否能按要求拆分；
// 于是，我们可以构造出状态转移方程： dp[i] = dp[j] && check( s[j, ..., i-1] )
// 其中，check(s) 函数表示检查字符串 s 是否出现在 wordDict 中，可以借助 hash 表实现
// 边界：dp[0] = true

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.*;

class P139_WordBreak {
    public static void main(String[] args) {
        Solution solution = new P139_WordBreak().new Solution();
        // TO TEST
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean answer = solution.wordBreak(s, wordDict);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> hash = new HashSet<String>(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for (int i = 0; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && hash.contains(s.substring(j,i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n^2)，一共需要计算 O(n) 个状态，每个状态点又需要 O(n) 个切分点的枚举，判断一个字符串是否在 hash 需要 O(1) 的时间复杂度；
// 空间复杂度：O(n），为 hash 表的空间大小