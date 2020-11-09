/******************************* Java：最后一个单词的长度 *******************************/
//给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。 
//
// 如果不存在最后一个单词，请返回 0 。 
//
// 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。 
//
// 
//
// 示例: 
//
// 输入: "Hello World"
// 输出: 5
// 
// Related Topics 字符串 
// 👍 244 👎 0


/******************************* 题目思路 *******************************/
// 很基础的字符串遍历题
// 关键点在于 边界检查 和 考虑字符串末尾为空格 的情况

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P58_LengthOfLastWord {
    public static void main(String[] args) {
        Solution solution = new P58_LengthOfLastWord().new Solution();
        // TO TEST
        String s = new String("a b  ");
        int answer = solution.lengthOfLastWord(s);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLastWord(String s) {
            if (s.length() == 0 || s == null) return 0;
            int n = s.length();
            int count = 0;  // 用来计数最后一个字符串长度
            int i = n-1;    // 遍历的变量
            // 跳过字符串末尾的空格
            while (i >=0 && s.charAt(i) == ' ') {
                i--;
            }
            // 开始计数最后一个字符串的长度
            while (i >= 0) {
                if (s.charAt(i) == ' ') {
                    return count;
                }
                count++;
                i--;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)
// 空间复杂度 O(1)