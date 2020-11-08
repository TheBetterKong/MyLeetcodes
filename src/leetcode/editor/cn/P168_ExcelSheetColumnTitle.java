/******************************* Java：Excel表列名称 *******************************/
// 给定一个正整数，返回它在 Excel 表中相对应的列名称。
//
// 例如， 
//
//     1 -> A
//     2 -> B
//     3 -> C
//     ...
//     26 -> Z
//     27 -> AA
//     28 -> AB
//      ...
// 
//
// 示例 1: 
//
// 输入: 1
// 输出: "A"
// 
//
// 示例 2: 
//
// 输入: 28
// 输出: "AB"
// 
//
// 示例 3: 
//
// 输入: 701
// 输出: "ZY"
// 
// Related Topics 数学 
// 👍 283 👎 0


/******************************* 题目思路 *******************************/
// 本题转换一下，就是一个进制转换的题：
//      利用 “辗转相除法” 的思想：
//         先看一个 10 进制的例子，671 可以写成 （6 * 10 + 7）* 10 + 1，即 6 7 1
//         再看本题，701 可以写成 26 * 26 + 25，即 Z（26）Y（25）
//      注意 n-- 的细节

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P168_ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution solution = new P168_ExcelSheetColumnTitle().new Solution();
        // TO TEST
        int n = 701;
        String answer = solution.convertToTitle(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToTitle(int n) {
            StringBuilder stringBuilder = new StringBuilder();
            while (n != 0) {
                n--;    // A 是从 1 开始的，为了位数对应，-1 操作相当于让 A 从 0 开始编号
                stringBuilder.append((char) ('A' + n % 26));    // 依次获取 26 进制逻辑上的个位、十位、百位
                n /= 26;    // 位数转移一位

            }
            return stringBuilder.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(m),取决于 n / 26 的次数
// 空间复杂度 O(1)