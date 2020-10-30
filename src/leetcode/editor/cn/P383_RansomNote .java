/******************************* Java：赎金信 *******************************/
// 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面
// 的字符构成。如果可以构成，返回 true ；否则返回 false。
//
// (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。) 
//
// 
//
// 注意： 
//
// 你可以假设两个字符串均只含有小写字母。 
//
// canConstruct("a", "b") -> false
// canConstruct("aa", "ab") -> false
// canConstruct("aa", "aab") -> true
// 
// Related Topics 字符串 
// 👍 114 👎 0


/******************************* 题目思路 *******************************/
// 最直接的思路就是利用 hashmap 先遍历一遍杂志串，存储其每个字符出现的次数，然后再利用这个 hashmap 遍历赎金信

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

class P383_RansomNote {
    public static void main(String[] args) {
        Solution solution = new P383_RansomNote().new Solution();
        // TO TEST
        String ransomNote = "aa";
        String magazine = "aab";
        boolean answer = solution.canConstruct(ransomNote, magazine);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            for (char ch : magazine.toCharArray()) {
                if (map.containsKey(ch))
                    map.put(ch, map.get(ch) + 1);
                else
                    map.put(ch, 1);
            }
            for(char ch: ransomNote.toCharArray()){
                if(!map.containsKey(ch))  //如果赎金信里面的字母，杂志中没有
                    return false;
                else if(map.get(ch) == 0)   //如果杂志中的字符不够
                    return false;
                else map.put(ch, map.get(ch)-1);
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(m+n)
// 空间复杂度 O(m)
// 其中，m 为 杂志串 的长度，n 为 赎金信 的长度