/******************************* Java：最小覆盖子串 *******************************/
// 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。 
//
// 
//
// 示例 1： 
//
// 输入：s = "ADOBECODEBANC", t = "ABC"
// 输出："BANC"
// 
//
// 示例 2： 
//
// 输入：s = "a", t = "a"
// 输出："a"
// 
//
// 
//
// 提示： 
//
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
//
// 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 1157 👎 0


/******************************* 题目思路 *******************************/
// 本题的思想不难，但是对于滑动算法的逻辑有较高的要求，
// 另外，hash 集的对比函数也值得学习。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class P76_MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new P76_MinimumWindowSubstring().new Solution();
        // TO TEST
        String s = "ADOBECODEBANC", t = "ABC";
        String answer = solution.minWindow(s, t);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> hash_w = new HashMap<Character, Integer>(); // 窗口内的字符集
            Map<Character, Integer> hash_t = new HashMap<Character, Integer>(); // 字符串 s 的字符集

            int lenth_s = s.length();
            int lenth_t = t.length();

            // 遍历 t，将其字符及其出现的次数放置在 hash_t 里
            for (int i = 0; i < lenth_t; i++) {
                char c = t.charAt(i);
                hash_t.put(c, hash_t.getOrDefault(c, 0) + 1);
            }

            // 设置滑动窗口遍历 s
            int left = 0, right = -1;                               // 窗口边界
            int resl = -1, resr = -1, reslen = Integer.MAX_VALUE;   // 当前最小覆盖串的左右边界，及长度

            while (right < lenth_s) {
                right++;

                // 为了节省时间和空间，我们只存 t 里面有的字符用作对比分析
                if (right <lenth_s && hash_t.containsKey(s.charAt(right))) {
                    hash_w.put(s.charAt(right) , hash_w.getOrDefault(s.charAt(right), 0) + 1);
                }

                // 达到覆盖，试图找最小窗口
                while (check(hash_t, hash_w) && left <= right) {
                    // 覆盖串更小就更新
                    if (right - left + 1 < reslen) {
                        reslen = right - left + 1;
                        resl = left;
                        resr = right;
                    }
                    // 缩短窗口，继续检索能否更小
                    if (hash_t.containsKey(s.charAt(left))) {
                        hash_w.put(s.charAt(left), hash_w.getOrDefault(s.charAt(left), 0) - 1);
                    }
                    left++;
                }
            }

            return resl == -1 ? "" : s.substring(resl, resr + 1);
        }

        // 检查 a 里的各个字符，b 里面是否比 a 多
        private boolean check(Map<Character, Integer> a, Map<Character, Integer> b) {
            Iterator iter = a.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Character key = (Character) entry.getKey();
                Integer val = (Integer) entry.getValue();
                if (b.getOrDefault(key, 0) < val) {
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：
//      最坏情况下左右指针对 s 的每个元素各遍历一遍，哈希表中对 s 中的每个元素各插入、删除一次，对 t 中的元素各插入一次。
//      每次检查是否可行会遍历整个 t 的哈希表，哈希表的大小与字符集的大小有关，设字符集大小为 C，则渐进时间复杂度为 O(C⋅∣s∣+∣t∣)。
// 空间复杂度：O(C)，C 是字符串里可能出现的字符集的大小