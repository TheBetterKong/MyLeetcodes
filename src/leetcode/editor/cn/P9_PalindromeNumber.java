/******************************* Java：回文数 *******************************/
// 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1: 
//
// 输入: 121
// 输出: true
// 
//
// 示例 2: 
//
// 输入: -121
// 输出: false
// 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
// 输出: false
// 解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1299 👎 0


/******************************* 题目思路 *******************************/
// 反转 int 型的数，注意：判断什么时候反转到了一半的位置。

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P9_PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new P9_PalindromeNumber().new Solution();
        // TO TEST
        int x = 10;
        boolean answer = solution.isPalindrome(x);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(int x) {
            // 负数一定不是回文串，末尾连续为 0 的数也不是回文串，这里判断末位 0 是为了便于最终的 return x == revertedNumber / 10
            if (x < 0 || (x % 10 == 0 && x != 0)) return false;

            // 反转 x
            int revertedNumber = 0;
            while (x > revertedNumber) {
                // 由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，
                // 所以，当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了。
                revertedNumber = revertedNumber * 10 + x % 10;
                x /= 10;
            }

            // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
            // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
            // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
            return x == revertedNumber || x == revertedNumber / 10;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 时间复杂度 O(n），n 为 x 的位数
// 空间复杂度 O(1)
