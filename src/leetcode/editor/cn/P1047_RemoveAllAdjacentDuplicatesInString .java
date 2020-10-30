/******************************* Java：删除字符串中的所有相邻重复项 *******************************/
// 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
//
// 在 S 上反复执行重复项删除操作，直到无法继续删除。
//
// 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
//
//
//
// 示例：
//
// 输入："abbaca"
// 输出："ca"
// 解释：
// 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又
// 只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
//
//
//
//
// 提示：
//
//
// 1 <= S.length <= 20000
// S 仅由小写英文字母组成。
//
// Related Topics 栈
// 👍 93 👎 0


/******************************* 题目思路 *******************************/
// 直接利用 StringBuilder 来模拟栈的操作即可

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P1047_RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {
        Solution solution = new P1047_RemoveAllAdjacentDuplicatesInString().new Solution();
        // TO TEST
        String S = new String("abbaca");
        String answer = solution.removeDuplicates(S);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeDuplicates(String S) {
            // 利用下面的 StringBuilder 和 reslength 来模拟栈操作，便于返回字符串
            StringBuilder res = new StringBuilder();
            int reslength = 0;

            for (int i = 0; i < S.length(); i++) {
                if (reslength > 0 && S.charAt(i) == res.charAt(reslength-1)) {
                    res.deleteCharAt(reslength-1);
                    reslength --;
                }
                else {
                    res.append(S.charAt(i));
                    reslength ++;
                }
            }

            return res.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度：O(n)
// 空间复杂度：O(n)，n 为 S 的长度