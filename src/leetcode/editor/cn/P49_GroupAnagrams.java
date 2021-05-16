/******************************* Java：字母异位词分组 *******************************/
// 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
// 输出:
// [
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
// ]
//
// 说明： 
//
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 734 👎 0


/******************************* 题目思路 *******************************/
// 字符出现次数相同的字符串作为一组，这里有一个有意思的特性很符合 hash 表的特点：
//      （选择出现的字符和各字符出现的次数作为 key，那么 val 就是当前的 list）
// 现在的问题就是求每个字符串里，出现的字符以及每个字符出现的次数，为了节省空间，将其用一个字符串表示，例如：a1e1t1

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P49_GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new P49_GroupAnagrams().new Solution();
        // TO TEST
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> answer = solution.groupAnagrams(strs);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                // 统计每个字符出现的次数
                int[] count = new int[26];
                int lenth = str.length();
                for (int i = 0; i < lenth; i++) {
                    count[str.charAt(i) - 'a']++;
                }
                // 拼凑为字符串
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (count[i] != 0) {
                        sb.append((char) ('a'+i));
                        sb.append(count[i]);
                    }
                }
                String key = sb.toString();

                // 存入 hash 表里
                List<String> list = map.getOrDefault(key, new ArrayList<String>());
                list.add(str);
                map.put(key, list);
            }

            return new ArrayList<List<String>>(map.values());
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n(k+26))，n 是字符数组里字符串的数量，k 是字符串的最大长度
// 空间复杂度：O(n(k+26))