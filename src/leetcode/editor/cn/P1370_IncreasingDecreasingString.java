/***************************** Java：上升下降字符串 *******************************/
// 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
//
// 
// 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。 
// 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。 
// 重复步骤 2 ，直到你没法从 s 中选择字符。 
// 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。 
// 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。 
// 重复步骤 5 ，直到你没法从 s 中选择字符。 
// 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。 
// 
//
// 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。 
//
// 请你返回将 s 中字符重新排序后的 结果字符串 。 
//
// 
//
// 示例 1： 
//
// 输入：s = "aaaabbbbcccc"
// 输出："abccbaabccba"
// 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
// 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
// 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
// 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
// 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
// 
//
// 示例 2： 
//
// 输入：s = "rat"
// 输出："art"
// 解释：单词 "rat" 在上述算法重排序以后变成 "art"
// 
//
// 示例 3： 
//
// 输入：s = "leetcode"
// 输出："cdelotee"
// 
//
// 示例 4： 
//
// 输入：s = "ggggggg"
// 输出："ggggggg"
// 
//
// 示例 5： 
//
// 输入：s = "spo"
// 输出："ops"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 500 
// s 只包含小写英文字母。 
// 
// Related Topics 排序 字符串 
// 👍 26 👎 0


/******************************* 题目思路 *******************************/
// 类似桶排序的思想，构造 26 个桶，分别对应 26 个字符里每个字符出现的次数，
//      排序时，先从左到右扫描，再从右向左扫描，即可。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1370_IncreasingDecreasingString {
    public static void main(String[] args) {
        Solution solution = new P1370_IncreasingDecreasingString().new Solution();
        // TO TEST
        String s = "leetcode";
        String answer = solution.sortString(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String sortString(String s) {
            // 初始化，构造桶
            int[] bucket = new int[26];
            for (int i = 0; i < s.length(); i++) {
                bucket[s.charAt(i) - 'a']++;
            }

            // 接下来就是不停的左右扫描桶，将元素计入结果字符串中
            StringBuilder result = new StringBuilder();
            while (result.length() != s.length()) {
                // 先从小到大
                for (int i = 0; i < 26; i++) {
                    if (bucket[i] > 0) {
                        result.append((char)('a' + i));
                        bucket[i]--;
                    }
                }
                // 再从大到小
                for (int i = 25; i >= 0; i--) {
                    if (bucket[i] > 0) {
                        result.append((char)('a' + i));
                        bucket[i]--;
                    }
                }
            }

            return result.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......