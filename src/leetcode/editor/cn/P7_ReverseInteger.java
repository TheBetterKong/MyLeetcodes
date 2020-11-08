/******************************* Java：整数反转 *******************************/
// 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1: 
//
// 输入: 123
// 输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
// 输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
// 输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学 
// 👍 2303 👎 0


/******************************* 题目思路 *******************************/
// 反转，就是将整数的最后一位 （x%10） 取出来，放到 ans（ans*10） 的最高位上。
// 但是要注意，本题需要特别考虑一下溢出的情况。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P7_ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new P7_ReverseInteger().new Solution();
        // TO TEST
        int x = 124;
        int answer = solution.reverse(x);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            int ans = 0;
            while (x != 0) {
                if ((ans * 10) / 10 != ans) { // 判断 ans 是否溢出
                    ans = 0;
                    break;
                }
                ans = ans * 10 + x % 10;
                x = x / 10;
            }
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n)，n 为 x 的位数
// 空间复杂度 O(1)
