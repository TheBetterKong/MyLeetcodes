/******************************* Java：2的幂 *******************************/
// 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
// 示例 1: 
//
// 输入: 1
// 输出: true
// 解释: 2^0 = 1
//
// 示例 2: 
//
// 输入: 16
// 输出: true
// 解释: 2^4 = 16
//
// 示例 3: 
//
// 输入: 218
// 输出: false
// Related Topics 位运算 数学 
// 👍 259 👎 0


/******************************* 题目思路 *******************************/
// 两种思路：
//      x & (x-1) 用来将二进制位的最后一位变为 0 ；
//      x & (-x) 可以获取到二进制中最右边的 1，且其它位设置为 0；

/******************************* 题目代码 *******************************/
package leetcode.editor.cn;

class P231_PowerOfTwo {
    public static void main(String[] args) {
        Solution solution = new P231_PowerOfTwo().new Solution();
        // TO TEST
        int n = 16;
        boolean answer = solution.isPowerOfTwo(n);
        System.out.println(answer);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfTwo(int n) {
            if (n == 0) return false;
            long x = (long) n;
            return (x & (x - 1)) == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}

/******************************* 代码评价 *******************************/
// 请主要从时间和空间复杂度的角度，对算法性能分析
// ......
