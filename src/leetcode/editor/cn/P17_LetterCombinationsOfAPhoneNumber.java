/******************************* Java：电话号码的字母组合 *******************************/
// 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键 9 键相同）。注意 1 不对应任何字母。
//
//
// 示例 1：
// 
// 输入：digits = "23"
// 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2：
// 
// 输入：digits = ""
// 输出：[]
// 
//
// 示例 3：
// 
// 输入：digits = "2"
// 输出：["a","b","c"]
// 
//
// 
//
// 提示：
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1116 👎 0


/******************************* 题目思路 *******************************/
// 题目其实就是一个简单的排列组合问题：
//
// 在递归设计时，绕了很多圈都没绕清，官方解释还是很清楚的：
// 1. 首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
// 2. 回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）：
//      ① 该字符串初始为空。
//      ② 每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，
//         然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。
//      ③ 然后进行回退操作，遍历其余的字母排列。
//
// 回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。
// 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。


/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17_LetterCombinationsOfAPhoneNumber().new Solution();
        // TO TEST
        String digits = "23";
        List answer = solution.letterCombinations(digits);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<String>();
            if (digits.length() == 0) {
                return res;
            }
            Map<Character, String> phoneMap = new HashMap<Character, String>(){{
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};
            backtrack(res, phoneMap, digits, 0, new StringBuffer());
            return res;
        }

        // 递归遍历
        private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
            if (index == digits.length()) {
                combinations.add(combination.toString());
            } else {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                int lettersCount = letters.length();
                for (int i = 0; i < lettersCount; i++) {
                    combination.append(letters.charAt(i));
                    backtrack(combinations, phoneMap, digits, index + 1, combination);
                    combination.deleteCharAt(index);
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(3^m x 4^n)，
//           其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），
//           n 是输入中对应 4 个字母的数字个数（包括数字 7、9），m+n 是输入数字的总个数。
//           当输入包含 m 个对应 3 个字母的数字和 n 个对应 4 个字母的数字时，不同的字母组合一共有 3^m x 4^n 种，需要遍历每一种字母组合。
// 空间复杂度：O(m+n)，
//          除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，
//          哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n。
