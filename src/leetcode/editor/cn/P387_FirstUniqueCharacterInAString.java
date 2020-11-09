/******************************* Java：字符串中的第一个唯一字符 *******************************/
// 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
// 
//
// 示例： 
//
// s = "leetcode"
// 返回 0
//
// s = "loveleetcode"
// 返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 273 👎 0


/******************************* 题目思路 *******************************/
// 同样利用散列表存储字符串里，每个字符出现的次数。然后，再次遍历字符串找到首次不重复的字符；两次遍历，一次存储。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class P387_FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        Solution solution = new P387_FirstUniqueCharacterInAString().new Solution();
        // TO TEST
        String s = "loveleetcode";
        int answer = solution.firstUniqChar(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (char ch : s.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1)
                    return i;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(n)