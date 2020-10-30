/******************************* Java：反转字符串 *******************************/
// 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。 
//
// 
//
// 示例 1： 
//
// 输入：["h","e","l","l","o"]
// 输出：["o","l","l","e","h"]
// 
//
// 示例 2： 
//
// 输入：["H","a","n","n","a","h"]
// 输出：["h","a","n","n","a","H"]
// Related Topics 双指针 字符串 
// 👍 310 👎 0


/******************************* 题目思路 *******************************/
// 本题也是十分常规的题目，直接使用双指针，交换两侧的字符串即可。
// 但是，我这里想造作一下，用一个指针完成，当然了，这样引入了新的计算，会带来额外的时间开销其实完全没必要。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P344_ReverseString {
    public static void main(String[] args) {
        Solution solution = new P344_ReverseString().new Solution();
        // TO TEST
        char []s = {'H','a','n','n','a','h'};
        solution.reverseString(s);
        System.out.println(s);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void reverseString(char[] s) {
            if (s == null || s.length == 0 || s.length == 1) return;

            int n = s.length;
            for (int i = 0; i < n/2; i++) {
                char temp = s[n-1-i];
                s[n-1-i] = s[i];
                s[i] = temp;
            }
            return;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......